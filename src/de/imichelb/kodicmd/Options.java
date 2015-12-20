package de.imichelb.kodicmd;

public class Options {
	
	private String kodiIp;
	private String twitchName;
	
	private final String twitchNameKey = "twitchName";
	private final String kodiIpKey = "kodiIp";
	
	private static Options instance = null;
	
	private Persistance persistance;
	
	private Options(){}
	
	public static Options getInstance(){
		
		if(instance == null) {
			instance = new Options();
		}
		
		return instance;
	}
	
	/*
	 * This method must be called once after creating this Object
	 */
	public void setPersistanceManager(Persistance persistance) {

		this.persistance = persistance;
	}
	
	/*
	 * This method must be called once after creating this Object
	 */
	public void init() {
		
		this.twitchName = persistance.load(twitchNameKey);
		this.kodiIp = persistance.load(kodiIpKey);
	}
	
	public void setKodiIp(String ip){
		
		persistance.save(kodiIpKey, ip);
		this.kodiIp = ip;
	}
	
	public String getKodiIp(){
		
		return kodiIp;
	}
	
	public void setTwitchName(String user){
		
		persistance.save(twitchNameKey, user);
		this.twitchName = user;
	}

	public String getTwitchName() {

		return twitchName;
	}
	
}
