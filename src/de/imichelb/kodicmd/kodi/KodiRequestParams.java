package de.imichelb.kodicmd.kodi;

/*
 * KODI API JSON Request Parameters
 */
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
	private String value;
	
	/*
	 * Playlist Attributes
	 */
	private String file;
	private Integer playlistid;
	private Integer position;
	private Item item;
	
	/*
	 * AudioLib
	 */
	private Limits limits;
	private Sort sort;
	private Filter filter;
	
	public KodiRequestParams(){}
	
	private class Item{
		
		private String file;
		private Integer playlistid;
		private Integer albumid;
		private Integer position;
		
		public Item(){}
		
		public Item(int playListId, int position){
			
			playlistid = playListId;
			this.position = position;
		}
		
		public Item(String file){
			
			this.file = file;
		}
		
		public void setAlbum(int id){
			
			this.albumid = id;
		}
	}
	
	private class Limits {
		
		private Integer start;
		
		public Limits(int start) {
			
			this.start = start;
		}
	}
	
	private class Sort {
		
		private String method;
		
		public Sort(String method) {
			
			this.method = method;
		}
	}
	
	private class Filter {
		
		private Integer albumid;
		
		public Filter(int id) {
			
			this.albumid = id;
		}
	}
	
	/*
	 * Parameter Setter
	 */
	public void setClearPlayListParams(int playlistId) {
		
		this.playlistid = playlistId;
	}
		
	public void setOpenPlayListParams(int playlistId, int position) {
		
		this.item = new Item(playlistId, position);
	}
	
	public void setAddToPlayListParams(int playListId, String uri) {
		
		this.playlistid = playListId;
		this.item = new Item(uri);
	}
	
	public void setAddToPlayListParams(int playlistId, int albumId) {
		
		this.playlistid = playlistId;
		this.item = new Item();
		item.setAlbum(albumId);
	}
	
	public void setSendTextParams(String text) {
		
		this.text = text;
	}
	
	public void setPlayerCtrlParams(int playerId, String[] properties) {
		
		this.playerid = playerId;
		this.properties = properties;
	}
	
	public void setVideoLibParams(String[] properties) {
		
		this.properties = properties;
	}
	
	public void setAlbumLibParams(String[] properties, String sortBy, int limit) {
		
		this.properties = properties;
		this.sort = new Sort(sortBy);
		this.limits = new Limits(limit);
	}
	
	public void setAlbumSongsParams(String[] properties, String sortBy, int albumId) {
		
		this.properties = properties;
		this.sort = new Sort(sortBy);
		this.filter = new Filter(albumId);
	}
	
	public void setSeekParams(int playerid, String value){
		
		this.playerid = playerid;
		this.value = value;
	}
	
	public void setGoToParams(int playerid, String value){
		
		this.playerid = playerid;
		this.to = value;
	}
}