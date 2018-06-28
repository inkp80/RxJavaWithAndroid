package inkyu.naver.com.rxjavaforandroid.starred;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import inkyu.naver.com.rxjavaforandroid.R;
import inkyu.naver.com.rxjavaforandroid.model.GithubRepo;
import io.reactivex.annotations.Nullable;

/**
 * Created by inkyu.park on 2018. 6. 27..
 */

public class GithubRepoBaseAdapter extends BaseAdapter {

	private List<GithubRepo> githubRepoList = new ArrayList<>();


	@Override
	public int getCount() {
		return githubRepoList.size();
	}

	@Override
	public GithubRepo getItem(int position) {
		if(position < 0 || githubRepoList.size() <= position) {
			return null;
		} else {
			return githubRepoList.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = (convertView != null ? convertView : createView(parent));
		GithubRepoViewHolder viewHolder = (GithubRepoViewHolder) view.getTag();
		viewHolder.setGithubRepo(getItem(position));
		return view;
	}


	private void setGithubRepoList(@Nullable List<GithubRepo> repoList){
		if(repoList == null) {
			return;
		}
		githubRepoList.clear();
		githubRepoList.addAll(repoList);
		// githubRepoList = repoList;
		// Question 여기서 githubRepoList = repoList 하는 것과 githubRepo.addAll(repoList)의 성능 상의 차이는?
	}

	private View createView(ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		View view = inflater.inflate(R.layout.item_github_repo, parent, false);
		GithubRepoViewHolder viewHolder = new GithubRepoViewHolder(view);
		view.setTag(viewHolder);
		return view;

	}

	private static class GithubRepoViewHolder{
		private TextView textRepoName;
		private TextView textRepoDescription;
		private TextView textLanguage;
		private TextView textStars;

		public GithubRepoViewHolder(View view){
			textRepoName = (TextView) view.findViewById(R.id.text_repo_name);
			textRepoDescription = view.findViewById(R.id.text_repo_description);
			textLanguage = view.findViewById(R.id.text_language);
			textStars = view.findViewById(R.id.text_stars);
		}

		public void setGithubRepo(GithubRepo githubRepo){
			textRepoName.setText(githubRepo.name);
			textRepoDescription.setText(githubRepo.description);
			textLanguage.setText(githubRepo.language);
			textStars.setText(githubRepo.stargazersCount);
		}
	}
}
