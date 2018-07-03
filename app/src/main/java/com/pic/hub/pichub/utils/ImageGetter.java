package com.pic.hub.pichub.utils;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageGetter extends AsyncTask<String, Integer, String> {
    private String requestUrl, imageId;
    private ProgressDialog progressDialog;
    private boolean set_wall;


    public ImageGetter(ProgressDialog progressDialog, boolean set_wall) {
        this.progressDialog = progressDialog;
        this.set_wall=set_wall;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        requestUrl = strings[0];
        imageId = strings[1];
        int count;
        try {
            URL url = new URL(requestUrl);
            URLConnection connection = url.openConnection();
            connection.connect();

            int fileLength = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());

            File dir = Environment.getExternalStoragePublicDirectory("PicHub"); //.PicHub dot makes this directory hidden to the user

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(dir, imageId + ".jpg");
            FileOutputStream out = new FileOutputStream(file);

            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) ((total * 100) / fileLength));
                out.write(data, 0, count);
            }

            out.flush();
            out.close();
            input.close();

        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        if (set_wall) {
            ImageStorage.setWallpaper(imageId, progressDialog.getContext());
            Toast.makeText(progressDialog.getContext(), "Set successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
