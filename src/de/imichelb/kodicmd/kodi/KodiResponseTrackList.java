package de.imichelb.kodicmd.kodi;

import java.util.ArrayList;

import android.graphics.Bitmap;
import de.imichelb.kodicmd.model.Album;
import de.imichelb.kodicmd.model.Track;

/*
 * KODI API AlbumList Response
 */
@SuppressWarnings("unused")
public class KodiResponseTrackList {

	private String jsonrpc;
	private int id;
	private Result result;	
	private Error error;
	
	private class Error{
		
		private Integer code;
		private String message;
	}
	
	private class Result {
		
		private Limits limits;
		private ArrayList<Track> songs;
	}
	
	private class Limits {
		
		private Integer start;
		private Integer end;
		private Integer total;
	}
	
	public ArrayList<Track> getTrackList() {
		
		return this.result.songs;
	}

}
