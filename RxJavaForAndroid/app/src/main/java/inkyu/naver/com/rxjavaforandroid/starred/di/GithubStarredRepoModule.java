package inkyu.naver.com.rxjavaforandroid.starred.di;


import android.content.Context;

import dagger.Binds;
import dagger.Module;
import inkyu.naver.com.rxjavaforandroid.di.qualifier.ActivityContext;
import inkyu.naver.com.rxjavaforandroid.di.scope.ActivityScope;
import inkyu.naver.com.rxjavaforandroid.starred.GithubStarredRepoActivity;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

@Module
public abstract class GithubStarredRepoModule {
	@ActivityScope
	@Binds
	@ActivityContext
	abstract Context bindContext(GithubStarredRepoActivity githubStarredRepoActivity);
}
