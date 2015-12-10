package de.imichelb.kodicmd.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.imichelb.kodicmd.Options;
import de.imichelb.kodicmd.R;

public class OptionsFragment extends Fragment {
	
	private EditText ip;
	private EditText twitchName;
	private Button btn;
	private Options opt;
	private Context context;
	
	public OptionsFragment(Context context){
		
		this.context = context;
		
		opt = Options.getInstance();
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.options, container, false);

		ip = (EditText) rootView.findViewById(R.id.kodi_ip);
		twitchName = (EditText) rootView.findViewById(R.id.twitchname);
		btn = (Button) rootView.findViewById(R.id.save_button);
        
        setValues();
        activateButton();
        
        return rootView;
    }
	
	private void activateButton() {
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				storeValues();
			}
		});
	}

	private void setValues(){

		ip.setText(opt.getKodiIp());
		twitchName.setText(opt.getTwitchName());
	}
	
	private void storeValues(){
		
		String newIp = ip.getText().toString();
		String newName = twitchName.getText().toString();
				
		opt.setKodiIp(newIp);
		opt.setTwitchName(newName);
		
		Toast.makeText(context, "Options saved!", Toast.LENGTH_LONG).show();
	}
}
