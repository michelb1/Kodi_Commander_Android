package de.imichelb.kodicmd.twitch;

import java.util.ArrayList;

/*
 * Twitch User API JSON Response
 */
public class TwitchUserResponse {

	public int _total;
	public ArrayList<Follow> follows = new ArrayList<Follow>();
	public Link _links;
	
	public class Follow{
		
		public Channel channel;
		
		public class Channel{
			
			String name;
		}
	}
	
	public class Link{
		
		public String self;
		public String next;
		public String previous;
	}
}
