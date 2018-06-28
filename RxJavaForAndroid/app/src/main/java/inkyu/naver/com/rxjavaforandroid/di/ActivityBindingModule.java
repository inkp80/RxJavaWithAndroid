package inkyu.naver.com.rxjavaforandroid.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import inkyu.naver.com.rxjavaforandroid.starred.GithubStarredRepoActivity;
import inkyu.naver.com.rxjavaforandroid.di.scope.ActivityScope;
import inkyu.naver.com.rxjavaforandroid.starred.di.GithubStarredRepoModule;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

@Module
public abstract class ActivityBindingModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = GithubStarredRepoModule.class)
	abstract GithubStarredRepoActivity mainActivity();
}
