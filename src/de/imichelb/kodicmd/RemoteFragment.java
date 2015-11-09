package de.imichelb.kodicmd;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.R;

public class RemoteFragment extends Fragment {
	
	public RemoteFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View remoteView = inflater.inflate(R.layout.remote, container, false);
        
        activateButtons(remoteView);
        
        return remoteView;
    }
	
	private void activateButtons(View view){
		
		addListener(R.id.remoteLeft,KodiCommand.LEFT,view);
		addListener(R.id.remoteRight,KodiCommand.RIGHT,view);
		addListener(R.id.remoteUp,KodiCommand.UP,view);
		addListener(R.id.remoteDown,KodiCommand.DOWN,view);
		addListener(R.id.remoteCenter,KodiCommand.ENTER,view);
		addListener(R.id.remoteBackward,KodiCommand.LEFT,view);
		addListener(R.id.remoteForward,KodiCommand.RIGHT,view);
		addListener(R.id.remotePlay,KodiCommand.PLAY,view);
		addListener(R.id.remoteStop,KodiCommand.STOP,view);
	}
	
	private void addListener(int resId, KodiCommand cmd, View view){
		
		ImageView image = (ImageView) view.findViewById(resId);
		image.setOnClickListener(new RemoteButtonListener(cmd));
	}
}
