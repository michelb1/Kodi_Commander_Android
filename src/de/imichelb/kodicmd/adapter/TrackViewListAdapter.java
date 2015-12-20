package de.imichelb.kodicmd.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.model.Track;

public class TrackViewListAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<Track> trackList;
	private Bitmap image;
	
	public TrackViewListAdapter(Context context, ArrayList<Track> trackList, Bitmap image) {
		
		this.context = context;
		this.trackList = trackList;
		this.image = image;
	}
	
	@Override
	public int getCount() {
		
		return trackList.size();
	}

	@Override
	public Object getItem(int pos) {
		
		return trackList.get(pos);
	}

	@Override
	public long getItemId(int pos) {

		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.track_list_item, parent, false);
		}
		
		String song = trackList.get(position).getTitle();
		int trackNr = trackList.get(position).getTrack();
		int duration = trackList.get(position).getDuration();

		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(convertToTime(duration));
		
		TextView artist = (TextView) convertView.findViewById(R.id.artist);
		artist.setText(trackNr + ". " + song);
		
		ImageView cover = (ImageView) convertView.findViewById(R.id.cover);
		cover.setImageBitmap(image);
		
		return convertView;
	}
	
	private String convertToTime(int duration) {
		
		String minutesStr;
		String secondsStr;
		
		int minutes = duration / 60;
		int seconds = duration - (minutes*60);
		
		if(minutes < 10) 
			minutesStr = "0" + minutes;
		else 
			minutesStr = "" + minutes;
		
		if(seconds < 10)
			secondsStr = "0" + seconds;
		else
			secondsStr = "" + seconds;
		
		return minutesStr + ":" + secondsStr;
	}

}
