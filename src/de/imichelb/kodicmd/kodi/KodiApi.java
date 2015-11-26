package de.imichelb.kodicmd.kodi;

/*
 * Not used for now
 */
public interface KodiApi {
	
	public void keyUp();
	
	public void keyDown();
	
	public void keyLeft();
	
	public void keyRight();
	
	public void keyEnter();
	
	public void stop();
	
	public void play();
	
	public void forward();
	
	public void backward();
	
	public void stepForward();
	
	public void stepBackward();
	
	public void back();
	
	public void title();
	
	public void info();
	
	public void addPlaylist();
	
	public void openPlaylist();
	
	public void openStream(String uri);

}
