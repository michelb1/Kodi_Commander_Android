package de.imichelb.kodicmd;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.imichelb.kodicmd.R;

public class YoutubeFragment extends Fragment {
	
	public YoutubeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.youtube, container, false);
         
        return rootView;
    }
}
