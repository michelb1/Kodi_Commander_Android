package de.imichelb.kodicmd.kodi;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.Gson;

import android.util.Log;
import de.imichelb.kodicmd.model.Options;
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
	
	private void addToPlaylist(String uri) throws MalformedURLException, IOException {
		
		String method = "Playlist.Add";
		KodiRequestParams params = new KodiRequestParams(getPlayListId(),uri);
		KodiRequest req = new KodiRequest(method,params);
		
		execute(req);
	}
	
	private void openPlaylist() throws MalformedURLException, IOException {
		
		String method = "Player.Open";
		KodiRequestParams params = new KodiRequestParams(getPlayListId());
		KodiRequest req = new KodiRequest(method,params);
		
		execute(req);
	}
	
	private void clearPlayList() {
		
		//TODO
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
	private int getPlayListId() throws MalformedURLException, IOException {
				
		String method = "Player.GetProperties";
		String[] props = {"playlistid","position"};
		
		int playerId = getPlayerId();
		if(playerId == -1) playerId = 1; //TODO: Change this!
		
		KodiRequestParams params = new KodiRequestParams(playerId,props);
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
	
	public void keyUp() throws MalformedURLException, IOException {
				
		basicInput("Input.Up");
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
		
		int id = getPlayerId();
		
		if(id == -1)
			basicInput("Input.Select");
		else
			basicInput("Input.ShowOSD");
	}
	
	public void stop() throws MalformedURLException, IOException {
		
		String method = "Player.Stop";
		
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams(id,(String[])null);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	public void play() throws MalformedURLException, IOException {
		
		String method = "Player.PlayPause";
		int id = getPlayerId();
		
		if(id != -1) {
			KodiRequestParams params = new KodiRequestParams(id,(String[])null);
			KodiRequest req = new KodiRequest(method,params);
			
			execute(req);
		}
	}
	
	public void forward() {
		
		//TODO
	}
	
	public void backward() {

		//TODO
	}
	
	public void stepForward() {
		
		//TODO
	}
	
	public void stepBackward() {
		
		//TODO
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
		
		//TODO: add return statements and parameters to the playlist methods to avoid redundant requests
		stop();
		addToPlaylist(uri);
		openPlaylist();
	}
}
