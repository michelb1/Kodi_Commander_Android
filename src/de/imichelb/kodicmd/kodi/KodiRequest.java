package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Request
 */
public class KodiRequest {

	public String jsonrpc;
	public int id;
	public String method;
	public Params params;
		
	public KodiRequest(KodiCommand cmd, String uri){
		
		jsonrpc = "2.0";
		id = 1;
		params = null;
		method = getCommand(cmd,uri);
	}
	
	private String getCommand(KodiCommand cmd, String uri) {
		
		String command;
		
		switch(cmd){
		
			case UP: command = "Input.Up";break;
			case DOWN: command = "Input.Down";break;
			case LEFT: command = "Input.Left";break;
			case RIGHT: command = "Input.Right";break;
			case ENTER: command = "Input.Select";break;
			case STOP: command = "Player.Stop";params = new ParamsPlayer();break;
			case PLAY: command = "Player.PlayPause";params = new ParamsPlayer();break;
			
			//TODO: The "Input.Select" Commands are Placeholders
			case FORWARD: command = "Input.Select";break;
			case BACKWARD: command = "Input.Select";break;
			case STEP_FORWARD: command = "Input.Select";break;
			case STEP_BACKWARD: command = "Input.Select";break;
			
			case BACK: command = "Input.Back";break;
			case TITLE: command = "Input.ShowCodec";break;
			case MENU: command = "Input.Home";break;
			case INFO: command = "Input.Info";break;		
			case PLAYLIST_ADD: command = "Playlist.Add";params = new ParamsPlayList(uri);break;
			case PLAYLIST_OPEN: command = "Player.Open";params = new ParamsPlayList();break;
			
			default: command = "";break;		
		}
		
		return command;
	}
	
	/*
	 * Parameter Classes
	 */
	public interface Params{
		
	}
	
	public class ParamsPlayer implements Params{
		
		int playerid = 1;			
	}
	
	public class ParamsPlayList implements Params{
				
		Integer playlistid;
				
		Item item = null;
		
		public ParamsPlayList(){
			
			item = new Item();
		}
		
		public ParamsPlayList(String uri){
				
			playlistid = 1;
			item = new Item(uri);
		}
		
		public class Item{
			
			String file;
			Integer playlistid;
			Integer position;
			
			public Item(){
				
				playlistid = 1;
				position = 0;
			}
			
			public Item(String file){
				
				this.file = file;
			}
						
		}
			
	}
	
}
