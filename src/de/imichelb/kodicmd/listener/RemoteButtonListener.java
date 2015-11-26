package de.imichelb.kodicmd.listener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import de.imichelb.kodicmd.kodi.KodiCommand;
import de.imichelb.kodicmd.tasks.KodiCommandTask;

public class RemoteButtonListener implements OnClickListener, OnTouchListener{
	
	private KodiCommand cmd;
	private Context context;
	
	private Vibrator vibe;
		
	public RemoteButtonListener(Context context, KodiCommand cmd){
		
		this.cmd = cmd;
		this.context = context;
		
		vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	@Override
	public void onClick(View view) {
		
		vibe.vibrate(30);
		
		new KodiCommandTask(context, cmd).execute();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		
		switch(event.getAction())
        {
			case MotionEvent.ACTION_DOWN: view.setActivated(true); break;
			case MotionEvent.ACTION_UP: view.setActivated(false); break;
        }
		
		return false;
	}

}
