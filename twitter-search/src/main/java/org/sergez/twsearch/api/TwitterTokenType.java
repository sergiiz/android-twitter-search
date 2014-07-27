package org.sergez.twsearch.api;

import com.google.gson.annotations.SerializedName;

/**
 * @author Sergii Zhuk
 *         Date: 24.06.2014
 *         Time: 23:53
 */
public class TwitterTokenType {

	@SerializedName("token_type")
	public String tokenType;

	@SerializedName("access_token")
	public String accessToken;

}
