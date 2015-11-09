package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Response
 */
public class KodiResponse {
	
	public String jsonrpc;
	public int id;
	public String result;
	public Error error;
	
	public class Error{
		
		public Integer code;
		public String message;
	}
}
