package de.imichelb.kodicmd.kodi;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.Gson;

import android.util.Log;
import de.imichelb.kodicmd.Options;
import de.imichelb.kodicmd.utils.HttpUtil;

public class Kodi {
	
	private final String path = "/jsonrpc?request=";
	
	private Options opt;
	private HttpUtil http;
	private Gson json;
	private String baseUri;
	
	public Kodi(){
		
		opt = Options.getInstance();
		http = new HttpUtil();
		json = new Gson();
		
		baseUri = "http://"+opt.getKodiIp()+path;
	}
		
	private String execute(KodiRequest req) throws IOException, MalformedURLException{
		
		return execute(req,"");
	}
	
	/*
	 * sends HTTP Request to Kodi
	 */
	private String execute(KodiRequest req, String uri) throws IOException, MalformedURLException{
			
		String request = json.toJson(req);
		Log.i("[JSON-REQUEST]", request);
		
		String response = http.get(baseUri+request);
		Log.i("[JSON-RESPONSE]", response);
		
		return response;
	}
	
	/*
	 * add uri to playlist
	 */
	private void addToPlaylist(String uri, int id) throws MalformedURLException, IOException {
		
		String method = "Playlist.Add";
		KodiRequestParams params = new KodiRequestParams();
		params.setAddToPlayListParams(id, uri);
		KodiRequest req = new KodiRequest(method,params);
		
		execute(req);
	}
	
	/*
	 * Start the playlist
	 */
	private void openPlaylist(int id) throws MalformedURLException, IOException {
		
		String method = "Player.Open";
		KodiRequestParams params = new KodiRequestParams();
		params.setOpenPlayListParams(id);
		KodiRequest req = new KodiRequest(method,params);
		
		execute(req);
	}
	
	/*
	 * Clears every item in the playlist
	 */
	private void clearPlayList(int id) throws MalformedURLException, IOException {
		
		String method = "Playlist.Clear";
		KodiRequestParams params = new KodiRequestParams();
		params.setClearPlayListParams(id);
		KodiRequest req = new KodiRequest(method,params);
		
		execute(req);
	}
	
	/*
	 * Gets the id of the active Player
	 */
	private int getPlayerId() throws MalformedURLException, IOException {
				
		String method = "Player.GetActivePlayers";
		KodiRequest req = new KodiRequest(method);		
		
		String response = execute(req);
		
		KodiResponsePlayer kodiResponse = json.fromJson(response, KodiResponsePlayer.class);
		
		Log.i("[PlayerID]", ""+kodiResponse.getId());
		
		return kodiResponse.getId();
	}
	
	/*
	 * Gets the id of the active Playlist
	 */
	@SuppressWarnings("unused")
	private int getPlayListId() throws MalformedURLException, IOException {
				
		String method = "Player.GetProperties";
		String[] props = {"playlistid","position"};
		
		int playerId = getPlayerId();
		
		//No Player active -> no active playlist
		if(playerId == -1) return -1;
		
		KodiRequestParams params = new KodiRequestParams();
		params.setPlayerCtrlParams(playerId, props);
		KodiRequest req = new KodiRequest(method,params);		
		
		String response = execute(req);
		
		KodiResponsePlayList kodiResponse = json.fromJson(response, KodiResponsePlayList.class);
		
		Log.i("[PlayListID]", ""+kodiResponse.getId());
		
		return kodiResponse.getId();
	}
	
	/*
	 * Basic Input Command which don't use Parameters
	 */
	private void basicInput(String method) throws MalformedURLException, IOException{
		
		KodiRequest req = new KodiRequest(method);		
		
		execute(req);
	}
	
	private void seek(String value) throws MalformedURLException, IOException {
		
		String method = "Player.Seek";
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams();
			params.setSeekParams(id, value);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	private void goTo(String value) throws MalformedURLException, IOException {
		
		String method = "Player.GoTo";
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams();
			params.setGoToParams(id, value);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	public void keyUp() throws MalformedURLException, IOException {
		
		int id = getPlayerId();
				
		if(id == -1)
			basicInput("Input.Up");
		else
			basicInput("Input.ShowOSD");		
	}
	
	public void keyDown() throws MalformedURLException, IOException {
		
		basicInput("Input.Down");	
	}
	
	public void keyLeft() throws MalformedURLException, IOException {
		
		basicInput("Input.Left");	
	}
	
	public void keyRight() throws MalformedURLException, IOException {
		
		basicInput("Input.Right");	
	}
	
	public void keyEnter() throws MalformedURLException, IOException {
		
		basicInput("Input.Select");
	}
	
	public void stop() throws MalformedURLException, IOException {
		
		String method = "Player.Stop";
		
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams();
			params.setPlayerCtrlParams(id, null);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	public void play() throws MalformedURLException, IOException {
		
		String method = "Player.PlayPause";
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams();
			params.setPlayerCtrlParams(id, null);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	public void forward() throws MalformedURLException, IOException {
		
		seek("smallforward");
	}
	
	public void backward() throws MalformedURLException, IOException {

		seek("smallbackward");	
	}
	
	public void stepForward() throws MalformedURLException, IOException {
		
		goTo("next");
	}
	
	public void stepBackward() throws MalformedURLException, IOException {
		
		goTo("previous");
	}
	
	public void back() throws MalformedURLException, IOException {

		basicInput("Input.Back");
	}
	
	public void title() throws MalformedURLException, IOException {
		
		int id = getPlayerId();
		
		if(id == -1)
			basicInput("Input.ContextMenu");
		else
			basicInput("Input.ShowCodec");
		
	}
	
	public void info() throws MalformedURLException, IOException {
		
		basicInput("Input.Info");
	}
	
	public void menu() throws MalformedURLException, IOException {
		
		basicInput("Input.Home");
	}
	
	public void openStream(String uri) throws MalformedURLException, IOException {
		
		int id = 1; //The id of the Video Playlist is always "1"
		
		stop();
		clearPlayList(id);
		addToPlaylist(uri,id);
		openPlaylist(id);
	}

	public void sendText(String text) throws MalformedURLException, IOException {
		
		String method = "Input.SendText";

		KodiRequestParams params = new KodiRequestParams();
		params.setSendTextParams(text);
		KodiRequest req = new KodiRequest(method,params);
			
		execute(req);
	}
	
	public void getTvShows() throws MalformedURLException, IOException {
	
		String method = "VideoLibrary.GetTVShows";
		String[] props = {"title"};
		
		KodiRequestParams params = new KodiRequestParams();
		params.setVideoLibParams(props);
		KodiRequest req = new KodiRequest(method,params);
			
		execute(req);
	}
}
