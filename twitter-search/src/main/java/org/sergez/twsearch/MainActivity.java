package org.sergez.twsearch;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import org.sergez.twsearch.util.ActivityHelper;


public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityHelper.navigateTo(this, new WelcomeFragment(), false, R.id.container_main);
		getFragmentManager().addOnBackStackChangedListener(this);
		//Handle when activity is recreated like on orientation Change
		shouldDisplayHomeUp();
	}

	@Override
	public void onBackStackChanged() {
		shouldDisplayHomeUp();
	}

	public void shouldDisplayHomeUp() {
		//Enable Up button only  if there are entries in the back stack
		boolean canback = getFragmentManager().getBackStackEntryCount() > 0;
		getActionBar().setDisplayHomeAsUpEnabled(canback);
	}

	@Override
	public boolean onNavigateUp() {
		//This method is called when the up button is pressed. Just the pop back stack.
		getFragmentManager().popBackStack();
		return true;
	}

}
