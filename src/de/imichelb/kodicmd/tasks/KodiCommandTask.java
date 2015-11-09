package de.imichelb.kodicmd.tasks;

import com.google.gson.Gson;

import android.os.AsyncTask;
import de.imichelb.kodicmd.Options;
import de.imichelb.kodicmd.helper.HttpUtil;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.kodi.KodiRequest;
import de.imichelb.kodicmd.kodi.KodiResponse;

public class KodiCommandTask extends AsyncTask<String, String, String>{
	
	private KodiCommand cmd;
	private final String path = "/jsonrpc?request=";
	
	private Options opt;
	private HttpUtil http;
	private Gson json;
	
	public KodiCommandTask(KodiCommand cmd){
		
		this.cmd = cmd;
		
		opt = Options.getInstance();
		json = new Gson();
		http = new HttpUtil();
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		String kodiRequest = "http://"+opt.getKodiIp()+path;
		
		String request = json.toJson(new KodiRequest(cmd,""));
		String response = http.get(kodiRequest+request);
		
		KodiResponse kodiResponse = json.fromJson(response, KodiResponse.class); 
		
		return kodiResponse.result;
	}
	

}
