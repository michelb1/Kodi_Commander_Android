package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Request
 */
public class KodiRequest {

	public final String jsonrpc = "2.0";
	
	public int id;
	public String method;
	public KodiRequestParams params;
	
	public KodiRequest(String method){

		this.id = 1;
		this.method = method;
		this.params = null;
	}
	
	public KodiRequest(String method, KodiRequestParams params){

		this.id = 1;
		this.method = method;
		this.params = params;
	}

}
