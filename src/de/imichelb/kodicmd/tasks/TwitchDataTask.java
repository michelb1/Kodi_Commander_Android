package de.imichelb.kodicmd.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.adapter.TwitchViewListAdapter;
import de.imichelb.kodicmd.model.TwitchItem;
import de.imichelb.kodicmd.twitch.Twitch;
import de.imichelb.kodicmd.twitch.TwitchItemListener;

public class TwitchDataTask extends AsyncTask<Object, Object, Object>{
	
	private ProgressBar progress;
	private ListView list;
	private Context context;
	private TwitchViewListAdapter adapter;
	private TwitchItemListener listener;
	
	public TwitchDataTask(Context context, ProgressBar progress, ListView list, TwitchItemListener listener){
		
		this.progress = progress;
		this.list = list;
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		
		if(!result.equals("")){
			
			Toast.makeText(context, (Integer)result, Toast.LENGTH_LONG).show();
			
		} else {
			
			list.setAdapter(adapter);
			progress.setVisibility(View.GONE);
			list.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onPreExecute() {
		
		progress.setVisibility(View.VISIBLE);
		list.setVisibility(View.GONE);
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		
		Twitch twitch = new Twitch();
		
		ArrayList<TwitchItem> twitchitems = new ArrayList<TwitchItem>();
		
		try {
			//invokes the Twitch Server Query
			twitchitems = twitch.getTwitchItems();
			
		} catch (MalformedURLException e) {
			
			return R.string.twitch_error;
			
		} catch (IOException e) {
			
			return R.string.twitch_error;
		}
		
		//Send the List to the Fragment class
		listener.setTwitchList(twitchitems);
		
		adapter = new TwitchViewListAdapter(context, twitchitems);
		
		return "";
	}
	
	

}
