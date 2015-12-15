package de.imichelb.kodicmd.tasks;

import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.widget.Toast;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.kodi.KodiCmdWrapper;
import de.imichelb.kodicmd.kodi.Command;

public class KodiCommandTask extends AsyncTask<Object, Object, Object>{
	
	private Command cmd;
	private String uri;
	private Context context;
	
	public KodiCommandTask(Context context){
		
		this.context = context;
	}
	
	public KodiCommandTask(Context context, Command cmd){
		
		this.cmd = cmd;
		this.uri = "";
		this.context = context;
	}
	
	public KodiCommandTask(Context context, Command cmd, String uri){
		
		this.cmd = cmd;
		this.uri = uri;
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		
		final KodiCommandTask task = this;
		
		new CountDownTimer(15000, 15000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
								
			}
			
			@Override
			public void onFinish() {
				
				if (task.getStatus() == AsyncTask.Status.RUNNING) {
					
	                task.cancel(false);	
				}
			}
		}.start();
	}

	@Override
	protected Object doInBackground(Object... params) {
					
		try {
			//execute the Kodi Command
			new KodiCmdWrapper(cmd, uri);
			
		} catch (MalformedURLException e) {
			
			return R.string.kodi_error;
			
		} catch (IOException e) {
			
			return R.string.kodi_error;
		}
		
		return "";
	}

	@Override
	protected void onCancelled(Object result) {
		
		Toast.makeText(context, R.string.kodi_timeout, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPostExecute(Object result) {
		
		if(!result.equals("")){
			
			Toast.makeText(context, (Integer)result, Toast.LENGTH_LONG).show();
		}
	}	

}
