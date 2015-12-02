package de.imichelb.kodicmd.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.kodi.Command;
import de.imichelb.kodicmd.tasks.KodiCommandTask;

public class TextInputFragment extends DialogFragment implements OnClickListener{

	private EditText text;
	private Context context;
	
	public TextInputFragment(Context context) {
		
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.text_input_dialog, container, false);
        
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		text = (EditText) rootView.findViewById(R.id.kodiText);
		rootView.findViewById(R.id.send_button).setOnClickListener(this);
        
        return rootView;
	}

	@Override
	public void onClick(View v) {
		
		String input = text.getText().toString();
		new KodiCommandTask(context, Command.SEND_TEXT, input).execute();
		
		this.dismiss();
	}
	
}
