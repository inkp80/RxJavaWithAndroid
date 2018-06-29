package inkyu.naver.com.rxjavaforandroid.starred;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import inkyu.naver.com.rxjavaforandroid.R;
import inkyu.naver.com.rxjavaforandroid.di.qualifier.ActivityContext;
import inkyu.naver.com.rxjavaforandroid.di.scope.ActivityScope;
import inkyu.naver.com.rxjavaforandroid.model.GithubRepo;

/**
 * Created by inkyu.park on 2018. 6. 27..
 */

@ActivityScope
public class GithubRepoRecyclerViewAdapter extends RecyclerView.Adapter<GithubRepoRecyclerViewAdapter.GithubRecyclerViewHolder>{
	private static final String TAG = GithubRepoRecyclerViewAdapter.class.getName();

	private Context mContext;

	private List<GithubRepo> githubRepoList = new ArrayList<>();

	@Inject
	public GithubRepoRecyclerViewAdapter(@ActivityContext Context context){
		this.mContext = context;
	}

	public void setGitHubRepos(@Nullable List<GithubRepo> githubRepoList) {
		if (githubRepoList == null) {
			return;
		}
		this.githubRepoList.clear();
		this.githubRepoList.addAll(githubRepoList);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public GithubRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.item_github_repo, parent, false);
		return new GithubRecyclerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull GithubRecyclerViewHolder holder, int position) {
		holder.textRepoName.setText(githubRepoList.get(position).name);
		holder.textRepoDescription.setText(githubRepoList.get(position).description);
		holder.textLanguage.setText("Language : " + githubRepoList.get(position).language);
		holder.textStars.setText("Stars : " + githubRepoList.get(position).stargazersCount);
	}

	@Override
	public int getItemCount() {
		return githubRepoList.size();
	}

	public class GithubRecyclerViewHolder extends RecyclerView.ViewHolder{
		TextView textRepoName;
		TextView textRepoDescription;
		TextView textLanguage;
		TextView textStars;

		public GithubRecyclerViewHolder(View itemView) {
			super(itemView);

			textRepoName = itemView.findViewById(R.id.text_repo_name);
			textRepoDescription = itemView.findViewById(R.id.text_repo_description);
			textLanguage = itemView.findViewById(R.id.text_language);
			textStars = itemView.findViewById(R.id.text_stars);

			itemView.setOnClickListener(
				v -> Toast.makeText(mContext, textRepoName.getText().toString(), Toast.LENGTH_SHORT).show());
		}
	}
}
