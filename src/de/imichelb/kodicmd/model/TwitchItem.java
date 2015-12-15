package de.imichelb.kodicmd.model;

import android.graphics.Bitmap;

public class TwitchItem implements ImageCache{

	private String streamer;
	private String game;
	private String imageUrl;
	private int viewer;
	private Bitmap previewImg;

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
	
	@Override
	public Bitmap getPreviewImg() {
		
		return previewImg;
	}

	@Override
	public void setPreviewImg(Bitmap previewImg) {
		
		this.previewImg = previewImg;
	}

}
