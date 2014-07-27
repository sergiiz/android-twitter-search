package org.sergez.twsearch.event;

/**
 * @author Sergii Zhuk
 *         Date: 24.06.2014
 *         Time: 23:43
 */
public class SearchTweetsEvent {

	public final String hashtag;
	public final String twitterToken;

	public SearchTweetsEvent(String twitterToken, String hashtag) {
		this.hashtag = hashtag;
		this.twitterToken = twitterToken;
	}


}
