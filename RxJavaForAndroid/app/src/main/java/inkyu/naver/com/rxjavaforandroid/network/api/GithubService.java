package inkyu.naver.com.rxjavaforandroid.network.api;

import java.util.List;

import inkyu.naver.com.rxjavaforandroid.model.GithubRepo;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by inkyu.park on 2018. 6. 27..
 */

public interface GithubService {
	@GET("users/{user}/starred")
	io.reactivex.Observable<List<GithubRepo>> getStarredRepositories(@Path("user") String userName);
}
