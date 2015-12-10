package de.imichelb.kodicmd;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PersitanceImpl implements Persistance{

	private SharedPreferences pref;
	
	private final String defaultValue = "default";
	
	public PersitanceImpl(Context context) {
		
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}
		
	@Override
	public void save(String key, String value) {
						
		Editor edit = pref.edit();
		
		edit.putString(key, value);
		
		edit.commit();
	}

	@Override
	public String load(String key) {
		
		String result = pref.getString(key, defaultValue);
		
		return result;
	}	
	
}
