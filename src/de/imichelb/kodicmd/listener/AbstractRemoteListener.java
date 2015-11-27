package de.imichelb.kodicmd.listener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public abstract class AbstractRemoteListener implements OnClickListener, OnTouchListener{

	protected Context context;
	protected Vibrator vibe;
		
	public AbstractRemoteListener(Context context){
		
		this.context = context;
		
		vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
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
