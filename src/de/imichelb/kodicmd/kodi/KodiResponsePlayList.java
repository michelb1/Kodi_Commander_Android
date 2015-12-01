package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Response
 */
@SuppressWarnings("unused")
public class KodiResponsePlayList {
	
	private String jsonrpc;
	private int id;
	private Result result;
	private Error error;
	
	private class Error{
		
		private Integer code;
		private String message;
	}
	
	private class Result {
		
		private Integer playlistid;
		private Integer position;
	}
	
	public int getId(){
				
		if(result == null)
			return -1;
		else
			return result.playlistid;
	}
}
