package de.imichelb.kodicmd.listener;

import android.content.Context;
import android.view.View;

public class RemoteDialogListener extends AbstractRemoteListener{
		
	public RemoteDialogListener(Context context) {

		super(context);
	}

	@Override
	public void onClick(View view) {
		
		vibe.vibrate(30);
		
		//TODO: Open Text Input Dialog		
	}

}
