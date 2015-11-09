package de.imichelb.kodicmd;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.imichelb.kodicmd.R;

public class OptionsFragment extends Fragment {
	
	public OptionsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.options, container, false);
         
        return rootView;
    }
}
