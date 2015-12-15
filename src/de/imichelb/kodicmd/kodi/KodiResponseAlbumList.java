package de.imichelb.kodicmd.kodi;

import java.util.ArrayList;

import android.graphics.Bitmap;
import de.imichelb.kodicmd.model.Album;

/*
 * KODI API AlbumList Response
 */
@SuppressWarnings("unused")
public class KodiResponseAlbumList {

	private String jsonrpc;
	private int id;
	private Result result;
	private Limits limits;
	private Error error;
	
	private class Error{
		
		private Integer code;
		private String message;
	}
	
	private class Result {
		
		ArrayList<Album> albums;
	}
	
	private class Limits {
		
		private Integer start;
		private Integer end;
		private Integer total;
	}
	
	public ArrayList<Album> getAlbumList() {
		
		return this.result.albums;
	}

}
