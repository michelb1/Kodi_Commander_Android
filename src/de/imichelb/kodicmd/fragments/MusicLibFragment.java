package de.imichelb.kodicmd.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.adapter.AlbumViewListAdapter;
import de.imichelb.kodicmd.adapter.TrackViewListAdapter;
import de.imichelb.kodicmd.kodi.Command;
import de.imichelb.kodicmd.kodi.MusicLibInterface;
import de.imichelb.kodicmd.model.Album;
import de.imichelb.kodicmd.model.Track;
import de.imichelb.kodicmd.tasks.AlbumListTask;
import de.imichelb.kodicmd.tasks.KodiCommandTask;
import de.imichelb.kodicmd.tasks.TrackListTask;

public class MusicLibFragment extends Fragment implements MusicLibInterface{
	
	private Context context;
	private ListView albumView;
	private ListView trackView;
	private TextView text;
	private ProgressBar progress;
	private State state;
	
	private int currentAlbumId;
	private Bitmap trackListImage;
	
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
        
        initBackBtn(rootView);
        
        return rootView;
    }

	private void setState( State state) {
		
		this.state = state;
		
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
	
	/*
	 * Change from Track View to Album View when pressing back
	 */
	private void initBackBtn(View view) {

		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.setOnKeyListener( new OnKeyListener()
		{
		    @Override
		    public boolean onKey( View v, int keyCode, KeyEvent event )
		    {	    	
		        if( keyCode == KeyEvent.KEYCODE_BACK )
		        {	        	
		        	if(state == State.TRACKLIST) {
		        		setState(State.ALBUMLIST);
		        	} 
		        	
		        	return true;
		        }
		        return false;
		    }
		} );
	}
	
	private void loadAlbumList() {
		
		setState(State.LOADING);
		
		new AlbumListTask(context, this).execute();
	}
	
	@Override
	public void displayAlbumList(final ArrayList<Album> albumList) {
		
		setState(State.LOADING);
		
		if(albumList.isEmpty()) {
			
			setState(State.EMPTYLIST);
			
		} else {
			
			//add click listener
			final MusicLibInterface musicIf = this;
			albumView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> adapter, View view, int pos, long lPos) {

					//Set the Cover as preview image for the tracklist
					trackListImage = albumList.get(pos).getPreviewImg(); 
					
					currentAlbumId = albumList.get(pos).getAlbumId();
					
					new TrackListTask(context, currentAlbumId, musicIf).execute();
				}
			});
			
			//create list
			AlbumViewListAdapter adapter = new AlbumViewListAdapter(context, albumList);
			albumView.setAdapter(adapter);
			
			//show list
			setState(State.ALBUMLIST);
		}

	}

	@Override
	public void displayTrackList(final ArrayList<Track> trackList) {
		
		setState(State.LOADING);
		
		//create list
		TrackViewListAdapter adapter = new TrackViewListAdapter(context, trackList, trackListImage);
		trackView.setAdapter(adapter);
		
		//add click listener
		trackView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, final int pos, long lpos) {
				
				Toast.makeText(context, R.string.play_album, Toast.LENGTH_LONG).show();
				new KodiCommandTask(context, Command.PLAY_ALBUM, pos, currentAlbumId).execute();				
			}
		});
		
		setState(State.TRACKLIST);
	}
}
