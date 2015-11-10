package de.imichelb.kodicmd.model;

public class Options {
	
	private String kodiIp;
	private String twitchName;
	
	private static Options instance = null;
	
	private Options(){}
	
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
