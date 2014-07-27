package org.sergez.twsearch.event;

import org.sergez.twsearch.api.TweetList;

/**
 * @author Sergii Zhuk
 *         Date: 23.06.2014
 *         Time: 17:29
 */
public class SearchTweetsEventOk {

	public final TweetList tweetsList;

	public SearchTweetsEventOk(TweetList tweets) {
		this.tweetsList = tweets;
	}

}
