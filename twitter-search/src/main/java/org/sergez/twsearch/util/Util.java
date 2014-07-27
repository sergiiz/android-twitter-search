package org.sergez.twsearch.util;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;


/**
 * @author Sergii Zhuk
 *         Date: 20.05.2014
 *         Time: 14:56
 */
public final class Util {

	public static final int INTERNET_CONN_CHECK_TIMEOUT = 1500;

	private static final String TAG = Util.class.getName();

	private Util() {

	}

	public static void makeToast(Context context, CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static String getBase64String(String value) throws UnsupportedEncodingException {
		return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
	}

}
