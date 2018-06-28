package inkyu.naver.com.rxjavaforandroid;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import inkyu.naver.com.rxjavaforandroid.di.DaggerAppComponent;

/**
 * Created by inkyu.park on 2018. 6. 28..
 */

public class BaseApplication extends DaggerApplication {
	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		return DaggerAppComponent.builder().application(this).build();
	}
}
