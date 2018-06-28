package inkyu.naver.com.rxjavaforandroid.network;

import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import inkyu.naver.com.rxjavaforandroid.model.GithubRepo;
import inkyu.naver.com.rxjavaforandroid.network.api.GithubService;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by inkyu.park on 2018. 6. 27..
 */

public class GithubClient {
	private static final String GITHUB_BASE_URL = "https://api.github.com/";
	private static GithubClient instance;
	private GithubService githubService;

	private GithubClient() {
		final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();

		githubService = retrofit.create(GithubService.class);
	}

	public static GithubClient getInstance(){
		if(instance == null){
			instance = new GithubClient();
		}
		return instance;
	}

	public io.reactivex.Observable<List<GithubRepo>> getStarredRepos(@NonNull String userName){
		return githubService.getStarredRepositories(userName);
	}
}
