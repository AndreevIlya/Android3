package com.example.android3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView picture;
    private Button beginButton;
    private Button cancelButton;
    private TextView clickToBegin;
    private TextView clickToCancel;
    private TextView success;

    private Disposable disposable;

    private static final int GET_PICTURE_REQUEST_CODE = 26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void setListeners() {
        beginButton.setOnClickListener((view) ->{
            Intent getPictureIntent = new Intent(Intent.ACTION_PICK);
            getPictureIntent.setType("image/*");
            startActivityForResult(getPictureIntent, GET_PICTURE_REQUEST_CODE);
        });
        cancelButton.setOnClickListener((view) -> {
            disposable.dispose();
            restoreViewAfterCancel();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == GET_PICTURE_REQUEST_CODE){
            try {
                if (intent != null) {
                    final Uri imageUri = intent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    changeViewAfterGetPicture();
                    savePNG(selectedImage);
                } else {
                    throw new NullPointerException();
                }
            } catch (FileNotFoundException | NullPointerException e) {
                Log.e("GET_PICTURE","Problem while acquiring a picture from gallery.",e);
            }
        }
    }

    private void savePNG(Bitmap img) {
        ImageConverter ic = new ImageConverter(img);
        disposable = ic.convertToPNG().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            changeViewAfterSuccess();
                            picture.setImageBitmap(img);
                        },
                        (e) -> Log.e("CONVERT_PICTURE","Problem while receiving a conversion result.",e));
    }

    private void initViews() {
        picture = findViewById(R.id.picture);
        beginButton = findViewById(R.id.get_picture);
        cancelButton = findViewById(R.id.cancel);
        clickToBegin = findViewById(R.id.click_to_begin);
        clickToCancel = findViewById(R.id.click_to_cancel);
        success = findViewById(R.id.success);
    }

    private void changeViewAfterGetPicture(){
        beginButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        clickToBegin.setVisibility(View.INVISIBLE);
        clickToCancel.setVisibility(View.VISIBLE);
    }

    private void changeViewAfterSuccess(){
        cancelButton.setVisibility(View.INVISIBLE);
        clickToCancel.setVisibility(View.INVISIBLE);
        success.setVisibility(View.VISIBLE);
    }

    private void restoreViewAfterCancel(){
        beginButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        clickToBegin.setVisibility(View.VISIBLE);
        clickToCancel.setVisibility(View.INVISIBLE);
        success.setVisibility(View.INVISIBLE);
        picture.setImageBitmap(null);
    }
}
