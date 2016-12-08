package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.Jamesglasgow.Cw.SaveFiles.SaveData;

/**
 *
 *
 */
public class SettingsActivity extends BaseActivity {
	SaveData SDPrefs;
	SharedPreferences mySharedPrefs;
	EditText PostcodeInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.item_5_layout, frameLayout);
		
		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		SDPrefs = new SaveData(mySharedPrefs);
		SDPrefs.setDefaultPrefs();
		PostcodeInfo=(EditText)findViewById(R.id.editText);
		

	}
	public void SaveRadio(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
			case R.id.RBRoad:
				if (checked)
					SDPrefs.savePreferences("mc_TYPE", 1);
					break;
			case R.id.RBHybrid:
				if (checked)
					SDPrefs.savePreferences("mc_TYPE", 2);
					break;
			case R.id.RBSatellite:
				if (checked)
					SDPrefs.savePreferences("mc_TYPE", 3);
					break;
			case R.id.RBTerrain:
				if (checked)
					SDPrefs.savePreferences("mc_TYPE", 4);
					break;
		}
	}
	public void SavePost(View view) {
		SDPrefs.savePreferences("mc_POST", PostcodeInfo.getText().toString());
	}
}
