package de.imichelb.kodicmd.kodi;

import java.io.IOException;
import java.net.MalformedURLException;

public class KodiCmdWrapper {
	
	public KodiCmdWrapper(Command cmd, String uri) throws MalformedURLException, IOException{
		
		executeCmd(cmd,uri);	
	}
	
	private void executeCmd(Command cmd, String uri) throws MalformedURLException, IOException{
		
		Kodi kodi = new Kodi();
		
		switch(cmd){
		
			case BACK: kodi.back();
				break;
			case BACKWARD: kodi.backward();
				break;
			case DOWN: kodi.keyDown();
				break;
			case ENTER: kodi.keyEnter();
				break;
			case FORWARD: kodi.forward();
				break;
			case INFO: kodi.info();
				break;
			case LEFT: kodi.keyLeft();
				break;
			case MENU: kodi.menu();
				break;
			case OPEN_STREAM: kodi.openStream(uri);
				break;
			case PLAY: kodi.play();
				break;
			case RIGHT: kodi.keyRight();
				break;
			case STEP_BACKWARD: kodi.stepBackward();
				break;
			case STEP_FORWARD: kodi.stepForward();
				break;
			case STOP: kodi.stop();
				break;
			case TITLE: kodi.title();
				break;
			case UP: kodi.keyUp();
				break;
			default:
				break;
	
		}
	}
}
