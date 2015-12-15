package de.imichelb.kodicmd.tasks;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import de.imichelb.kodicmd.model.ImageCache;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	
    private ImageView view;
    private ProgressBar progress;
    private ImageCache item;

    public DownloadImageTask(ImageView view, ProgressBar progress, ImageCache item) {
    	
        this.view = view;
        this.progress = progress;
        this.item = item;
    }
    
    public DownloadImageTask(ImageView view, ImageCache item) {
    	
        this.view = view;
        this.item = item;
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
        
        //cache the downloaded image
        item.setPreviewImg(bitmap);
        
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
    	
        view.setImageBitmap(result);
        
        if(progress != null)
        	progress.setVisibility(View.GONE);
    }

	@Override
	protected void onPreExecute() {
		
		if(progress != null)
			progress.setVisibility(View.VISIBLE);
	}    
}