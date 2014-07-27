package org.sergez.twsearch;

import android.app.Application;
import android.content.Context;
import com.squareup.otto.Bus;
import org.sergez.twsearch.api.ApiConstants;
import org.sergez.twsearch.api.TwitterApiService;
import org.sergez.twsearch.api.TwitterServiceProvider;
import org.sergez.twsearch.util.BusProvider;
import retrofit.RestAdapter;

/**
 * @author Sergii Zhuk
 *         Date: 24.06.2014
 *         Time: 22:19
 */
public class TwitterSearchApplication extends Application{
	private static TwitterSearchApplication mInstance;
	private static Context mAppContext;

	private TwitterServiceProvider mTwitterService;
	private Bus bus = BusProvider.getInstance();

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		this.setAppContext(getApplicationContext());
		mTwitterService = new TwitterServiceProvider(buildApi(), bus);
		bus.register(mTwitterService);
		bus.register(this); //listen to "global" events
	}

	private TwitterApiService buildApi() {
		return new RestAdapter.Builder()
				.setEndpoint(ApiConstants.TWITTER_SEARCH_URL)
				.build()
				.create(TwitterApiService.class);
	}

/*	@Subscribe
	public void onApiError(ApiErrorEvent event) {
		toast("Something went wrong, please try again.");
		Log.e("ReaderApp", event.getErrorMessage());
	}*/

	public static TwitterSearchApplication getInstance(){
		return mInstance;
	}
	public static Context getAppContext() {
		return mAppContext;
	}
	public void setAppContext(Context mAppContext) {
		this.mAppContext = mAppContext;
	}


}
