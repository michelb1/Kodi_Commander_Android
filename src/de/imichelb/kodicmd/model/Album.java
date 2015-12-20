package de.imichelb.kodicmd.model;

import android.graphics.Bitmap;

public class Album implements ImageCache{

	private int albumid;
	private String[] artist;
	private String title;
	private String label;
	private String thumbnail;
	
	private Bitmap cover;
	
	public int getAlbumId() {
		
		return albumid;
	}
	
	public String getArtist() {
		
		return artist[0];
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getLabel() {
		
		return label;
	}
	
	public String getThumbnail() {
		
		//add "25" for proper url encoding
		String url = thumbnail.replaceAll("%", "%25");
		
		return url;
	}
	
	@Override
	public void setPreviewImg(Bitmap image) {
		
		this.cover = image;
	}

	@Override
	public Bitmap getPreviewImg() {
		
		return cover;
	}
	
}
