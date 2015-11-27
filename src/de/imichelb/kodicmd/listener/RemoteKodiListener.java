package de.imichelb.kodicmd.listener;

import android.content.Context;
import android.view.View;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.tasks.KodiCommandTask;

public class RemoteKodiListener extends AbstractRemoteListener{
	
	private KodiCommand cmd;
		
	public RemoteKodiListener(Context context, KodiCommand cmd){
		
		super(context);
		
		this.cmd = cmd;
	}
	
	@Override
	public void onClick(View view) {
		
		vibe.vibrate(30);
		
		new KodiCommandTask(context, cmd).execute();
	}

}
