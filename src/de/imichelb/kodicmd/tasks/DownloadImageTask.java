package de.imichelb.kodicmd.tasks;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	
    ImageView view;

    public DownloadImageTask(ImageView view) {
    	
        this.view = view;
    }

    protected Bitmap doInBackground(String... urls) {
    	
        String urldisplay = urls[0];
        Bitmap bitmap = null;
        
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            
        } catch (Exception e) {
        	
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
    	
        view.setImageBitmap(result);
    }    
}