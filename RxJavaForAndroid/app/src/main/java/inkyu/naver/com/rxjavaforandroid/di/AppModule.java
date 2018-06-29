package inkyu.naver.com.rxjavaforandroid.di;

import javax.inject.Singleton;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import inkyu.naver.com.rxjavaforandroid.BaseApplication;
import inkyu.naver.com.rxjavaforandroid.di.qualifier.ApplicationContext;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

@Module
public abstract class AppModule {
	@Singleton
	@Binds
	@ApplicationContext
	abstract Context bindContext(BaseApplication baseApplication);
}
