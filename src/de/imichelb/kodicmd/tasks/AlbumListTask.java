package de.imichelb.kodicmd.tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import de.imichelb.kodicmd.R;
import de.imichelb.kodicmd.kodi.Kodi;
import de.imichelb.kodicmd.kodi.MusicLibInterface;
import de.imichelb.kodicmd.model.Album;

public class AlbumListTask extends AsyncTask<Object, Object, Object>{

	private Context context;
	private MusicLibInterface musicLib;
	private ArrayList<Album> albumList;
	
	public AlbumListTask(Context context, MusicLibInterface musicLib) {
	
		this.context = context;
		this.musicLib = musicLib;
		this.albumList = new ArrayList<Album>();
	}

	@Override
	protected Object doInBackground(Object... params) {
		
		Kodi kodi = new Kodi();
		
		try {
			albumList = kodi.getMusicAlbums();
						
		} catch (MalformedURLException e) {

			return R.string.kodi_error;
			
		} catch (IOException e) {

			return R.string.kodi_error;
		}
		
		return "";
	}

	@Override
	protected void onPostExecute(Object result) {
		
		if(!result.equals("")){
			
			Toast.makeText(context, (Integer)result, Toast.LENGTH_LONG).show();
		}
		
		musicLib.displayAlbumList(albumList);
	}

}
