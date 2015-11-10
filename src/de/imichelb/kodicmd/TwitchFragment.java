package de.imichelb.kodicmd;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.tasks.TwitchDataTask;

public class TwitchFragment extends Fragment {
	
	private View twitchView;
	private ProgressBar progress;
	private ListView list;
	private Context context;
	
	public TwitchFragment(Context context){
		
		this.context = context;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        twitchView = inflater.inflate(R.layout.twitch, container, false);
        progress = (ProgressBar) twitchView.findViewById(R.id.twitch_progress_bar);
        list = (ListView) twitchView.findViewById(R.id.twitch_stream_list);
         
        loadTwitchData();
        
        return twitchView;
    }
	
	private void hideContent(){
		
		list.setVisibility(View.GONE);
	}
	
	private void showContent(){
		
	}
	
	private void loadTwitchData(){
		
		new TwitchDataTask(context,progress,list).execute();
	}
}
