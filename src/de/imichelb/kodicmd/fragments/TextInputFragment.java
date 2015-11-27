package de.imichelb.kodicmd.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import de.imichelb.kodicmd.R;


public class TextInputFragment extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.text_input_dialog, container, false);
        
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        return rootView;
	}
	
}
