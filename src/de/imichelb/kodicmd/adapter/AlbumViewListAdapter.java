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
import de.imichelb.kodicmd.Options;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.model.Album;
import de.imichelb.kodicmd.tasks.DownloadImageTask;

public class AlbumViewListAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<Album> albumList;
	private Options opt;
	
	public AlbumViewListAdapter(Context context, ArrayList<Album> albumList) {
		
		this.context = context;
		this.albumList = albumList;
		opt = Options.getInstance();
	}
	
	@Override
	public int getCount() {
		
		return albumList.size();
	}

	@Override
	public Object getItem(int pos) {
		
		return albumList.get(pos);
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
			convertView = mInflater.inflate(R.layout.album_list_item, parent, false);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(albumList.get(position).getTitle());
		
		TextView artist = (TextView) convertView.findViewById(R.id.artist);
		artist.setText(albumList.get(position).getArtist());
		
		ImageView cover = (ImageView) convertView.findViewById(R.id.cover);
		String imgUrl = "http://" + opt.getKodiIp() + "/image/" + albumList.get(position).getThumbnail();	
		
		Bitmap img = albumList.get(position).getPreviewImg();
		
		if(img == null) {
			cover.setImageResource(R.drawable.cover_ph);
			new DownloadImageTask(cover, albumList.get(position)).execute(imgUrl);
			
		} else {
			cover.setImageBitmap(img);
		}
		
		return convertView;
	}

}
