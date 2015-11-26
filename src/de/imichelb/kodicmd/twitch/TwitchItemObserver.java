package de.imichelb.kodicmd.twitch;

import java.util.ArrayList;

import de.imichelb.kodicmd.model.TwitchItem;

public interface TwitchItemObserver {
	
	public void setTwitchList(ArrayList<TwitchItem> list);
}
