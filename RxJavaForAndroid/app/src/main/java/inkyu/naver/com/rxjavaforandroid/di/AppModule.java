package inkyu.naver.com.rxjavaforandroid.di;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import inkyu.naver.com.rxjavaforandroid.BaseApplication;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

@Module
public abstract class AppModule {
	@Binds
	abstract Context bindContext(BaseApplication baseApplication);
}
