package com.example.android3;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.FileOutputStream;

import io.reactivex.Completable;

class ImageConverter {
    private final Bitmap image;
    private static final String FILE_NAME = "image.png";

    ImageConverter(Bitmap image){
        this.image = image;
    }

    Completable convertToPNG(){
        return Completable.fromAction(() -> {
            try {
                Thread.sleep(5000);
                FileOutputStream  fos = new FileOutputStream(FILE_NAME, false);
                image.compress(Bitmap.CompressFormat.PNG, 95, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                Log.e("CONVERT_PICTURE","Problem while converting a picture.",e);
            }
        });
    }
}
