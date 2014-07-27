package org.sergez.twsearch.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

/**
 * @author Sergii Zhuk
 *         Date: 12.06.2014
 *         Time: 14:28
 */
public final class ActivityHelper {
	private static final String TAG = ActivityHelper.class.getName();

	private ActivityHelper() {

	}

	public static void navigateTo(Activity activity, final Fragment fragment, int containerId) {
		navigateTo(activity, fragment, null, true, false, false, containerId);
	}

	public static void navigateTo(Activity activity, final Fragment fragment, final boolean addToBackStack, int containerId) {
		navigateTo(activity, fragment, null, addToBackStack, false, false, containerId);
	}

	public static void navigateTo(Activity activity, final Fragment fragment, final FragmentTransaction transaction, final boolean addToBackStack,
	                              final boolean allowStateLoss, final boolean useTransition, int containerId) {
		final FragmentManager fm = activity.getFragmentManager();
		final FragmentTransaction ft = transaction == null ? fm.beginTransaction() : transaction;
		ft.setTransition(useTransition ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : FragmentTransaction.TRANSIT_NONE);

		ft.replace(containerId, fragment, fragment.getClass().getName());
		if (addToBackStack) {
			ft.addToBackStack(fragment.getClass().getName());
		}

		commitTransaction(ft, allowStateLoss);
	}


	private static void commitTransaction(final FragmentTransaction ft, final boolean allowStateLoss) {
		if (allowStateLoss) {
			ft.commitAllowingStateLoss();
		} else {
			ft.commit();
		}
	}



	private static void closeActivity(Activity activity) {
		Intent intent = activity.getIntent();
		activity.setResult(Activity.RESULT_OK, intent);
		activity.finish();
	}

	/*public static int getMainActivityContainer() {
		return R.id.container_main;
	}*/

}
