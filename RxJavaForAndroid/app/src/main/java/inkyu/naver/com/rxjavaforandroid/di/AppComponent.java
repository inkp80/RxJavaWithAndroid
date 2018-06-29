package inkyu.naver.com.rxjavaforandroid.di;

import javax.inject.Singleton;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import inkyu.naver.com.rxjavaforandroid.BaseApplication;
import inkyu.naver.com.rxjavaforandroid.starred.di.GithubStarredRepoModule;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

@Singleton
@Component(modules = {
	AppModule.class,
	ActivityBindingModule.class,
	AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {
	@Component.Builder
	interface Builder {
		@BindsInstance
		AppComponent.Builder application(Application application);

		AppComponent build();
	}
}
