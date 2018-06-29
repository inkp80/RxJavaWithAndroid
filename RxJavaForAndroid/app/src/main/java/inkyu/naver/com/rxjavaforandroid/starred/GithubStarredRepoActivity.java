package inkyu.naver.com.rxjavaforandroid.starred;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import dagger.android.support.DaggerAppCompatActivity;
import inkyu.naver.com.rxjavaforandroid.network.GithubClient;
import inkyu.naver.com.rxjavaforandroid.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GithubStarredRepoActivity extends DaggerAppCompatActivity {
	private static final String TAG = GithubStarredRepoActivity.class.getName();

	//views
	private RecyclerView recyclerView;
	private EditText etInputUserName;
	private Button btSearch;


	//instances
	private Disposable disposable;
	private GithubRepoBaseAdapter githubRepoBaseAdapter;

	@Inject
	GithubRepoRecyclerViewAdapter githubRepoRecyclerViewAdapter;

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
					githubRepoRecyclerViewAdapter.setGitHubRepos(githubRepos);
				},
				throwable -> Log.d(TAG, "RxJava http error...\n" + throwable.getMessage())
			);
	}
}
