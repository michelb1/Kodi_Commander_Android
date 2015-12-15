package de.imichelb.kodicmd.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.adapter.AlbumViewListAdapter;
import de.imichelb.kodicmd.kodi.MusicLibInterface;
import de.imichelb.kodicmd.model.Album;
import de.imichelb.kodicmd.model.Track;
import de.imichelb.kodicmd.tasks.AlbumListTask;

public class MusicLibFragment extends Fragment implements MusicLibInterface{
	
	private Context context;
	private ListView albumView;
	private ListView trackView;
	private TextView text;
	private ProgressBar progress;
	
	private enum State {
		
		LOADING,
		ALBUMLIST,
		TRACKLIST,
		EMPTYLIST;
	}
	
	public MusicLibFragment(Context context) {
		
		this.context = context;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.musiclib, container, false);
        
        albumView = (ListView) rootView.findViewById(R.id.album_list);
        trackView = (ListView) rootView.findViewById(R.id.track_list);
        progress = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        text = (TextView) rootView.findViewById(R.id.emptyList);
                
        loadAlbumList();
        
        return rootView;
    }

	private void setState( State state) {
		
		switch(state){
		
			case LOADING: 
				progress.setVisibility(View.VISIBLE);
				albumView.setVisibility(View.GONE);
				trackView.setVisibility(View.GONE);
				text.setVisibility(View.GONE);
				break;
			case ALBUMLIST:
				progress.setVisibility(View.GONE);
				albumView.setVisibility(View.VISIBLE);
				trackView.setVisibility(View.GONE);
				text.setVisibility(View.GONE);
				break;
			case TRACKLIST:
				progress.setVisibility(View.GONE);
				albumView.setVisibility(View.GONE);
				trackView.setVisibility(View.VISIBLE);
				text.setVisibility(View.GONE);
				break;
			case EMPTYLIST:
				progress.setVisibility(View.GONE);
				albumView.setVisibility(View.GONE);
				trackView.setVisibility(View.GONE);
				text.setVisibility(View.VISIBLE);
				break;
			default:
				break;
		}
	}
	
	private void loadAlbumList() {
		
		setState(State.LOADING);
		new AlbumListTask(context, this).execute();
	}
	
	private void loadTrackList(int albumId) {
		
		setState(State.LOADING);
	}
	
	@Override
	public void displayAlbumList(ArrayList<Album> albumList) {
		
		if(albumList.isEmpty()) {
			
			setState(State.EMPTYLIST);
			
		} else {
			
			AlbumViewListAdapter adapter = new AlbumViewListAdapter(context, albumList);
			albumView.setAdapter(adapter);
			setState(State.ALBUMLIST);
		}

	}

	@Override
	public void displayTrackList(ArrayList<Track> trackList) {
		// TODO Auto-generated method stub
		
		setState(State.TRACKLIST);
	}
}
