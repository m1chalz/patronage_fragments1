package com.example.rest.fragments.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fragment = new PlaceholderFragment();
		getFragmentManager().beginTransaction()
				.add(R.id.container, fragment)
				.commit();
	}


	@Override
	protected void onResume() {
		super.onResume();
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void run() {
				((PlaceholderFragment) fragment).setBackgroundColor(android.R.color.holo_red_dark);
			}
		}, 5000);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getFragmentManager().putFragment(outState, "contentFragment", fragment);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		RelativeLayout layout;
		int colorId = android.R.color.holo_green_dark;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			layout = (RelativeLayout) view.findViewById(R.id.relative);
			setBackgroundColor(colorId);
			if (savedInstanceState != null) {
				setBackgroundColor(savedInstanceState.getInt("color"));
			}
		}

		public void setBackgroundColor(int color) {
			layout.setBackgroundResource(color);
			colorId = color;
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("color", colorId);
		}
	}
}
