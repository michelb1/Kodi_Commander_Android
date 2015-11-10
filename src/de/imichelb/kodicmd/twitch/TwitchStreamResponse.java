package de.imichelb.kodicmd.twitch;

import java.util.ArrayList;

/*
 * Twitch Stream API JSON Response
 */
public class TwitchStreamResponse {

	public int _total = 0;
	public ArrayList<Stream> streams = new ArrayList<Stream>();
	public Link _links = new Link();
	
	public class Stream{
		        
		public String _id;
		public String game;
		public int viewers;
		public String created_at;
		public int video_height;
		public float average_fps;
		public Boolean is_playlist;
		public Preview preview = new Preview();
		public Channel channel = new Channel();
        
        public class Preview{
        	
        	public String small;
        	public String medium;
        	public String large;
        }
        
        public class Channel{
        	
        	public String display_name;
        	public String logo;
        	public String status;
        	public String _id;
        	public String name;
        }
	}
	
	public class Link{
		
		public String self;
		public String next;
		public String featured;
		public String summary;
		public String followed;
	}	
	
}


