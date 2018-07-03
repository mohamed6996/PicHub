package com.pic.hub.pichub.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ImageStorage {

    public static File getImage(String imagename) {
        File dir = Environment.getExternalStoragePublicDirectory("PicHub");
        if (dir.exists()) {
            File file = new File(dir.getPath() + "/" + imagename + ".jpg");
            return file;
        } else return null;


    }

    public static boolean checkifImageExists(String imagename) {
        Bitmap b = null;
        File file = ImageStorage.getImage(imagename);
        String path = file.getAbsolutePath();

        if (path != null)
            b = BitmapFactory.decodeFile(path);

        if (b == null || b.equals("")) {
            return false;
        }
        return true;
    }

    public static Bitmap getBitmap(String imagename) {
        Bitmap b;
        File file = ImageStorage.getImage(imagename);
        String path = file.getAbsolutePath();
        b = BitmapFactory.decodeFile(path);

        return b;
    }


    public static void setWallpaper(String imageId, Context context) {
        Bitmap bitmap = ImageStorage.getBitmap(imageId);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap);
             Toast.makeText(context, "set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
              Toast.makeText(context, "something bad happened", Toast.LENGTH_SHORT).show();
        }
    }
}