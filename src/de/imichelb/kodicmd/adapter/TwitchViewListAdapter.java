package de.imichelb.kodicmd.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.model.TwitchItem;
import de.imichelb.kodicmd.tasks.DownloadImageTask;

public class TwitchViewListAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<TwitchItem> items;
	
	public TwitchViewListAdapter(Context context, ArrayList<TwitchItem> items){
		
		this.context = context;		
		this.items = items;
	}
	
	@Override
	public int getCount() {
		
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.twitch_list_item, null);
        }
		
		TextView text = (TextView) convertView.findViewById(R.id.stream);
		text.setText(items.get(position).getStreamer());
		
		TextView game = (TextView) convertView.findViewById(R.id.game);
		game.setText(items.get(position).getGame());
		
		ImageView image = (ImageView) convertView.findViewById(R.id.preview);
		
		new DownloadImageTask(image).execute(items.get(position).getImageUrl());
		
		return convertView;
	}

}
