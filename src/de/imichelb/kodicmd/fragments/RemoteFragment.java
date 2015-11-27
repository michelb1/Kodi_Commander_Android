package de.imichelb.kodicmd.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.listener.RemoteKodiListener;
import de.imichelb.kodicmd.listener.AbstractRemoteListener;
import de.imichelb.kodicmd.listener.RemoteDialogListener;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.R.id;
import de.imichelb.kodicmd.R.layout;

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
			
		addListener(R.id.remoteLeft,KodiCommand.LEFT,view);
		addListener(R.id.remoteRight,KodiCommand.RIGHT,view);
		addListener(R.id.remoteUp,KodiCommand.UP,view);
		addListener(R.id.remoteDown,KodiCommand.DOWN,view);
		addListener(R.id.remoteCenter,KodiCommand.ENTER,view);
		addListener(R.id.remoteBackward,KodiCommand.LEFT,view);
		addListener(R.id.remoteForward,KodiCommand.RIGHT,view);
		addListener(R.id.remotePlay,KodiCommand.PLAY,view);
		addListener(R.id.remoteStop,KodiCommand.STOP,view);
		addListener(R.id.remoteCodec,KodiCommand.TITLE,view);
		addListener(R.id.remoteInfo,KodiCommand.INFO,view);
		addListener(R.id.remoteBack,KodiCommand.BACK,view);
		addListener(R.id.remoteStepBackward,KodiCommand.STEP_BACKWARD,view);
		addListener(R.id.remoteStepForward,KodiCommand.STEP_FORWARD,view);
		
		addListener(R.id.remoteMenu,KodiCommand.MENU,view);
		addListener(R.id.remoteText,null,view);

	}
	
	private void addListener(int resId, KodiCommand cmd, View view){
		
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
