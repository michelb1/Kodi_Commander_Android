package de.imichelb.kodicmd.tasks;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import de.imichelb.kodicmd.adapter.TwitchViewListAdapter;
import de.imichelb.kodicmd.model.TwitchItem;
import de.imichelb.kodicmd.twitch.Twitch;

public class TwitchDataTask extends AsyncTask<Object, Object, Object>{
	
	private ProgressBar progress;
	private ListView list;
	private Context context;
	private Twitch twitch;
	private TwitchViewListAdapter adapter;
	
	public TwitchDataTask(Context context, ProgressBar progress, ListView list){
		
		this.progress = progress;
		this.list = list;
		this.context = context;
		twitch = new Twitch();
	}
	
	@Override
	protected void onPostExecute(Object result) {
		
		list.setAdapter(adapter);
		progress.setVisibility(View.GONE);
		list.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onPreExecute() {
		
		progress.setVisibility(View.VISIBLE);
		list.setVisibility(View.GONE);
	}

	@Override
	protected Object doInBackground(Object... arg0) {
			
		ArrayList<TwitchItem> twitchitems = twitch.getTwitchItems();
				
		adapter = new TwitchViewListAdapter(context, twitchitems);
		
		return null;
	}

}
