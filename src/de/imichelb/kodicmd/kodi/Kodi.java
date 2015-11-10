package de.imichelb.kodicmd.kodi;

import com.google.gson.Gson;


import de.imichelb.kodicmd.helper.HttpUtil;
import de.imichelb.kodicmd.model.Options;

public class Kodi {
	
	private final String path = "/jsonrpc?request=";
	
	private Options opt;
	private HttpUtil http;
	private Gson json;
	
	public Kodi(){
		
		opt = Options.getInstance();
		http = new HttpUtil();
		json = new Gson();
	}
	
	public KodiResponse execute(KodiCommand cmd){
		
		return execute(cmd,"");
	}

	public KodiResponse execute(KodiCommand cmd, String uri){
		
		String kodiRequest = "http://"+opt.getKodiIp()+path;
		
		String request = json.toJson(new KodiRequest(cmd,uri));
		String response = http.get(kodiRequest+request);
		
		KodiResponse kodiResponse = json.fromJson(response, KodiResponse.class); 
				
		return kodiResponse;
	}
	
	public void executeStream(String uri){
		
		execute(KodiCommand.STOP);
		execute(KodiCommand.PLAYLIST_ADD,uri);
		execute(KodiCommand.PLAYLIST_OPEN);
	}

}
