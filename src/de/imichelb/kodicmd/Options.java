package de.imichelb.kodicmd;

/*
 * TODO Store Options in File
 */
public class Options {
	
	private String kodiIp;
	private String twitchName;
	
	private static Options instance = null;
	
	private Options(){
		
		kodiIp = "192.168.0.111";
		twitchName = "athoril";
	}
	
	public static Options getInstance(){
		
		if(instance == null) {
			instance = new Options();
		}
		
		return instance;
	}
	
	public void setKodiIp(String ip){
		
		kodiIp = ip;
	}
	
	public String getKodiIp(){
		
		return kodiIp;
	}
	
	public void setTwitchName(String user){
		
		twitchName = user;
	}

	public String getTwitchName() {

		return twitchName;
	}
	
}
