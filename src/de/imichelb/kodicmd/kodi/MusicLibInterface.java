package de.imichelb.kodicmd.kodi;

import java.util.ArrayList;

import de.imichelb.kodicmd.model.Album;
import de.imichelb.kodicmd.model.Track;

public interface MusicLibInterface {

	public void displayAlbumList(ArrayList<Album> albumList);
	public void displayTrackList(ArrayList<Track> trackList);	
}
