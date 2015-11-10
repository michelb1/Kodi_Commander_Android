package de.imichelb.kodicmd.model;

public class TwitchItem {

	String streamer;
	String game;
	String imageUrl;
	int viewer;
	
	public TwitchItem(String streamer, String game, String imageUrl, int viewer) {
		
		this.streamer = streamer;
		this.game = game;
		this.imageUrl = imageUrl;
		this.viewer = viewer;
	}

	public String getStreamer() {
		
		return streamer;
	}

	public String getGame() {
		
		return game;
	}

	public String getImageUrl() {
		
		return imageUrl;
	}

	public int getViewer() {
		
		return viewer;
	}

}
