package com.Jamesglasgow.Cw.OpenGlasgow;

import com.Jamesglasgow.Cw.SaveFiles.SaveData;
import com.Jamesglasgow.Cw.adapters.HomeScreenGridViewAdapter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.GridView;

/**
 *
 */
public class HomeActivity extends BaseActivity {

	SaveData SDPrefs;
	SharedPreferences mySharedPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * We will not use setContentView in this activty Rather than we will
		 * use layout inflater to add view in FrameLayout of our base activity
		 * layout
		 */

		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.home_layout, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		SDPrefs = new SaveData(mySharedPrefs);
		SDPrefs.setDefaultPrefs();

	}


}
