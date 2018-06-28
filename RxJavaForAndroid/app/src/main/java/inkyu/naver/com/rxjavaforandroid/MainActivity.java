package inkyu.naver.com.rxjavaforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getName();

	//views
	private RecyclerView recyclerView;
	private EditText etInputUserName;
	private Button btSearch;


	//instances
	private Disposable disposable;
	private GithubRepoBaseAdapter githubRepoBaseAdapter;
	private GithubRepoRecyclerViewAdapter githubRepoRecyclerViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initInstances();
	}

	@Override
	public void onDestroy(){
		if(disposable != null && !disposable.isDisposed()){
			disposable.dispose();
		}
		super.onDestroy();
	}


	private void initViews(){
		recyclerView = findViewById(R.id.list_view_repos);
		etInputUserName = findViewById(R.id.edit_text_username);
		btSearch = findViewById(R.id.button_search);
		btSearch.setOnClickListener(v -> {
				final String userName = etInputUserName.getText().toString();
				if(!TextUtils.isEmpty(userName)) {
					getStarredRepos(userName);
				}
		});
	}

	private void initInstances(){
		githubRepoBaseAdapter = new GithubRepoBaseAdapter();
		initGithubRepoRecyclerView();
	}

	private void initGithubRepoRecyclerView(){
		githubRepoRecyclerViewAdapter = new GithubRepoRecyclerViewAdapter(this);
		recyclerView.setAdapter(githubRepoRecyclerViewAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	private void getStarredRepos(String userName) {
		disposable = GithubClient.getInstance()
			.getStarredRepos(userName)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				githubRepos -> {
					Log.d(TAG, "RxJava response from server..." + githubRepos.size());
					for (GithubRepo githubRepo : githubRepos) {
						Log.d(TAG, "repo name : " + githubRepo.name + "\nrepo desc : " + githubRepo.description);
					}
					githubRepoRecyclerViewAdapter.setGitHubRepos(githubRepos);
				},
				throwable -> Log.d(TAG, "RxJava http error...\n" + throwable.getMessage())
			);
	}
}
