package de.imichelb.kodicmd.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import de.imichelb.kodicmd.kodi.Command;
import de.imichelb.kodicmd.listener.RemoteKodiListener;
import de.imichelb.kodicmd.listener.AbstractRemoteListener;
import de.imichelb.kodicmd.listener.RemoteDialogListener;
import de.imichelb.kodicmd.R;

public class RemoteFragment extends Fragment {
	
	Context context;
	
	public RemoteFragment(Context context){
		
		this.context = context;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View remoteView = inflater.inflate(R.layout.remote, container, false);
        
        initButtons(remoteView);
        
        return remoteView;
    }
	
	private void initButtons(View view){
			
		addListener(R.id.remoteLeft,Command.LEFT,view);
		addListener(R.id.remoteRight,Command.RIGHT,view);
		addListener(R.id.remoteUp,Command.UP,view);
		addListener(R.id.remoteDown,Command.DOWN,view);
		addListener(R.id.remoteCenter,Command.ENTER,view);
		addListener(R.id.remoteBackward,Command.BACKWARD,view);
		addListener(R.id.remoteForward,Command.FORWARD,view);
		addListener(R.id.remotePlay,Command.PLAY,view);
		addListener(R.id.remoteStop,Command.STOP,view);
		addListener(R.id.remoteCodec,Command.TITLE,view);
		addListener(R.id.remoteInfo,Command.INFO,view);
		addListener(R.id.remoteBack,Command.BACK,view);
		addListener(R.id.remoteStepBackward,Command.STEP_BACKWARD,view);
		addListener(R.id.remoteStepForward,Command.STEP_FORWARD,view);
		
		addListener(R.id.remoteMenu,Command.MENU,view);
		addListener(R.id.remoteText,null,view);

	}
	
	private void addListener(int resId, Command cmd, View view){
		
		AbstractRemoteListener listener;
		
		//kodi listener
		if(cmd != null) {
			
			listener = new RemoteKodiListener(context, cmd);
		
		//special listener
		} else {
			
			listener = new RemoteDialogListener(context);
		}
			
		ImageView image = (ImageView) view.findViewById(resId);
		
		image.setSoundEffectsEnabled(false);
		
		image.setOnClickListener(listener);
		image.setOnTouchListener(listener);
	}
}
