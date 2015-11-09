package de.imichelb.kodicmd;

import android.view.View;
import android.view.View.OnClickListener;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.tasks.KodiCommandTask;

public class RemoteButtonListener implements OnClickListener{
	
	KodiCommand cmd;
		
	public RemoteButtonListener(KodiCommand cmd){
		
		this.cmd = cmd;
	}
	
	@Override
	public void onClick(View view) {
		
		new KodiCommandTask(cmd).execute();
	}

}
