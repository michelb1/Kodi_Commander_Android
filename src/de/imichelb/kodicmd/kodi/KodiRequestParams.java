package de.imichelb.kodicmd.kodi;

@SuppressWarnings("unused")
public class KodiRequestParams {
	
	/*
	 * Text input attributes
	 */
	private String text;
	
	/*
	 * Player Attributes
	 */
	private Integer playerid;
	private String[] properties;
	
	/*
	 * Player control attributes
	 */
	private String to;
	
	/*
	 * Playlist Attributes
	 */
	private String file;
	private Integer playlistid;
	private Integer position;
	private Item item;
	
	public KodiRequestParams(int playerId, String[] properties) {
		
		this.playerid = playerId;
		this.properties = properties;		
	}
	
	public KodiRequestParams(int playListId){
		
		this.item = new Item(playListId);
	}
	
	public KodiRequestParams(int playListId, String uri){
			
		this.playlistid = playListId;
		this.item = new Item(uri);
	}
	
	public KodiRequestParams(String text){
		
		this.text = text;
	}

	private class Item{
		
		public String file;
		public Integer playlistid;
		public Integer position;
		
		public Item(int playListId){
			
			playlistid = playListId;
			position = 0; //TODO: position should be a parameter
		}
		
		public Item(String file){
			
			this.file = file;
		}
					
	}
}