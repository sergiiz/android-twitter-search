package org.sergez.twsearch.api;

import android.util.Log;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import org.sergez.twsearch.TwitterSearchApplication;
import org.sergez.twsearch.event.*;
import org.sergez.twsearch.util.PrefsController;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.*;

import static org.sergez.twsearch.util.Util.getBase64String;

/**
 * @author Sergii Zhuk
 *         Date: 23.06.2014
 *         Time: 17:41
 */
public class TwitterServiceProvider {
	private static final String TAG = TwitterServiceProvider.class.getName();

	private TwitterApiService mApi;
	private Bus mBus;

	public TwitterServiceProvider(TwitterApiService api, Bus bus) {
		this.mApi = api;
		this.mBus = bus;
	}

	@Subscribe
	public void onLoadTweets(final SearchTweetsEvent event) {
		mApi.getTweetList("Bearer " + event.twitterToken, event.hashtag, new Callback<TweetList>() {
			@Override
			public void success(TweetList response, Response rawResponse) {
					mBus.post(new SearchTweetsEventOk(response));
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(TAG, error.toString(), error);
				mBus.post(new SearchTweetsEventFailed());
			}
		});
	}

	@Subscribe
	public void onGetToken(TwitterGetTokenEvent event) {
		try {
			mApi.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials", new Callback<TwitterTokenType>() {
				@Override
				public void success(TwitterTokenType token, Response response) {
					PrefsController.setAccessToken(TwitterSearchApplication.getAppContext(), token.accessToken);
					PrefsController.setTokenType(TwitterSearchApplication.getAppContext(), token.tokenType);
					mBus.post(new TwitterGetTokenEventOk());
				}

				@Override
				public void failure(RetrofitError error) {
					Log.e(TAG, error.toString(), error);
					mBus.post(new TwitterGetTokenEventFailed());
				}
			});
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.toString(), e);
		}
	}


	/*private static String getResponseBody(InputStream inputStream) {
		StringBuilder sb = new StringBuilder();
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

			String line = null;
			while ((line = bReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (UnsupportedEncodingException ex) {
			Log.e("LOG", "", ex);
		} catch (ClientProtocolException ex1) {
			Log.e("LOG", "", ex1);
		} catch (IOException ex2) {
			Log.e("LOG", "", ex2);
		}
		return sb.toString();
	}*/

	/*// converts a string of JSON data into a Twitter object
	private static TweetList jsonToTweetLost(String result) {
		TweetList twits = null;
		if (result != null && result.length() > 0) {
			try {
				Gson gson = new Gson();
				twits = gson.fromJson(result, TweetList.class);
			} catch (IllegalStateException ex) {
				Log.e("LOG", "",ex);
			}
		}
		return twits;
	}*/


}
