package de.imichelb.kodicmd;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.tasks.KodiCommandTask;

public class RemoteButtonListener implements OnClickListener{
	
	KodiCommand cmd;
	Context context;
		
	public RemoteButtonListener(Context context, KodiCommand cmd){
		
		this.cmd = cmd;
		this.context = context;
	}
	
	@Override
	public void onClick(View view) {
		
		new KodiCommandTask(context, cmd).execute();
	}

}
