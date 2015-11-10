package de.imichelb.kodicmd.tasks;

import android.os.AsyncTask;
import de.imichelb.kodicmd.kodi.Kodi;
import de.imichelb.kodicmd.kodi.KodiCommand;

public class KodiCommandTask extends AsyncTask<String, String, String>{
	
	private KodiCommand cmd;
	private String uri;
	
	public KodiCommandTask(KodiCommand cmd){
		
		this.cmd = cmd;
		this.uri = "";
	}
	
	public KodiCommandTask(KodiCommand cmd, String uri){
		
		this.cmd = cmd;
		this.uri = uri;
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		Kodi kodi = new Kodi();
		
		if(cmd == KodiCommand.OPEN_STREAM){
			
			kodi.executeStream(uri);
			
		} else {
			
			kodi.execute(cmd);
		}
		
		return "";
	}	

}
