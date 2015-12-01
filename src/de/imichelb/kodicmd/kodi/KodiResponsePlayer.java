package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Response
 */
@SuppressWarnings("unused")
public class KodiResponsePlayer {
	
	private String jsonrpc;
	private int id;
	private Result[] result;
	private Error error;
	
	private class Error{
		
		private Integer code;
		private String message;
	}
	
	private class Result {
		
		private Integer playerid;		
	}
	
	public int getId(){
		
		if(result.length == 0)
			return -1;
		else
			return result[0].playerid;
	}
}
