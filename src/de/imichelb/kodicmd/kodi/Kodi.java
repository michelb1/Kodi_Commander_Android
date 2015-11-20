package de.imichelb.kodicmd.kodi;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.Gson;

import de.imichelb.kodicmd.model.Options;
import de.imichelb.kodicmd.utils.HttpUtil;

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
	
	public KodiResponse execute(KodiCommand cmd) throws IOException, MalformedURLException{
		
		return execute(cmd,"");
	}

	public KodiResponse execute(KodiCommand cmd, String uri) throws IOException, MalformedURLException{
		
		String kodiRequest = "http://"+opt.getKodiIp()+path;
		
		String request = json.toJson(new KodiRequest(cmd,uri));
		String response = http.get(kodiRequest+request);
		
		KodiResponse kodiResponse = json.fromJson(response, KodiResponse.class); 
				
		return kodiResponse;
	}
	
	public void executeStream(String uri) throws IOException, MalformedURLException{
		
		execute(KodiCommand.STOP);
		execute(KodiCommand.PLAYLIST_ADD,uri);
		execute(KodiCommand.PLAYLIST_OPEN);
	}

}
