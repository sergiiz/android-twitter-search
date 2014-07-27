package org.sergez.twsearch.util;

import com.squareup.otto.Bus;

/**
 * @author Sergii Zhuk
 *         Date: 24.06.2014
 *         Time: 22:46
 *         <p/>
 *         TODO: migrate to dependency injection
 */
public class BusProvider {
	private static Bus mInstance = null;

	private BusProvider() {

	}

	public static Bus getInstance() {
		if (mInstance == null) {
			mInstance = new Bus();
		}
		return mInstance;
	}
}
