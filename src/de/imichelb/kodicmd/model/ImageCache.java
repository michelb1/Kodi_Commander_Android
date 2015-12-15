package de.imichelb.kodicmd.model;

import android.graphics.Bitmap;

public interface ImageCache {

	public void setPreviewImg(Bitmap image);
	public Bitmap getPreviewImg();
	
}
