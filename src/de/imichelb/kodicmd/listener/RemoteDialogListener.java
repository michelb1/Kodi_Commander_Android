package de.imichelb.kodicmd.listener;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import de.imichelb.kodicmd.fragments.TextInputFragment;

public class RemoteDialogListener extends AbstractRemoteListener{
		
	public RemoteDialogListener(Context context) {

		super(context);
	}

	@Override
	public void onClick(View view) {
		
		vibe.vibrate(30);
		
		FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
		
		TextInputFragment textInput = new TextInputFragment(context);
		
		textInput.show(fm, "Text Input Fragment");
	}

}
