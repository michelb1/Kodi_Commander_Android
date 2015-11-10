package de.imichelb.kodicmd.twitch;

import java.util.ArrayList;

import com.google.gson.Gson;

import de.imichelb.kodicmd.Options;
import de.imichelb.kodicmd.helper.HttpUtil;
import de.imichelb.kodicmd.model.TwitchItem;
import de.imichelb.kodicmd.twitch.TwitchStreamResponse.Stream;
import de.imichelb.kodicmd.twitch.TwitchUserResponse.Follow;

public class Twitch {

	private ArrayList<String> channelList;
	private TwitchStreamResponse onlineChannels;

	private HttpUtil http = new HttpUtil();
	private Gson json = new Gson();
	private Options options;
	
	public Twitch(){
		
		options = Options.getInstance();
		channelList = new ArrayList<String>();
		onlineChannels = null;
	}
	
	private void requestOnlineChannels(){

		StringBuilder cList = new StringBuilder();
		
		for(String channel: channelList){
			
			cList.append(channel).append(",");
		}
		
		String reqUri = "https://api.twitch.tv/kraken/streams?channel="+cList.toString();
		
		if(!channelList.isEmpty()){
			
			String resp = http.get(reqUri);
			onlineChannels = new TwitchStreamResponse();
			onlineChannels = json.fromJson(resp, TwitchStreamResponse.class);
		}		

	}
	
	private void requestChannelList(){
		
		int page = 1;
		String reqUri;
		String resp;
		
		reqUri = "https://api.twitch.tv/kraken/users/"+options.getTwitchName()+"/follows/channels";
		
		try {
			
			resp = http.get(reqUri);
			
		} catch (Exception e) {
			
			return;
		}
		
		TwitchUserResponse response = new TwitchUserResponse();
		response = json.fromJson(resp, TwitchUserResponse.class);
		
		for(Follow list: response.follows){
			
			channelList.add(list.channel.name);
		}
		
		//There are only 25 Channels per page
		if(response._total > (25*page)){
			
			reqUri = response._links.next;
			resp = http.get(reqUri);
			
			response = new TwitchUserResponse();
			response = json.fromJson(resp, TwitchUserResponse.class);
						
			for(Follow list: response.follows){
				
				channelList.add(list.channel.name);
			}
			
			page++;
		}
	}
	
	private void requestLists(){
		
		requestChannelList();
		requestOnlineChannels();
	}
	
	public ArrayList<TwitchItem> getTwitchItems(){
		
		requestLists();
		
		ArrayList<TwitchItem> items = new ArrayList<TwitchItem>();
		
		for(Stream list: onlineChannels.streams){
			
			String streamer;
			String game;
			String imageUrl;
			int viewer;
			
			streamer = list.channel.display_name;
			game = list.game;
			imageUrl = list.preview.medium;
			viewer = list.viewers;
			
			items.add(new TwitchItem(streamer, game, imageUrl, viewer));
		}
		
		return items;
	}
}
