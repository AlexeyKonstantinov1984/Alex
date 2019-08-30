package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private static int TAKE_PICTURE_REQUEST = 1;
    private Uri outputFileUri;
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);



    }

    public void onMainActivityClick(View view)
    {
        Intent intent = new Intent (MainActivity.this, login.class) ;
        startActivity(intent);

    }



    public void onClick(View view) {


        if (Build.VERSION.SDK_INT >= 23) {
            int readPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
                MainActivity.this.requestPermissions(
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_ID_READ_WRITE_PERMISSION);
                return;
            }
        }



        //getThumbnailPicture();
        saveFullImage();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE_REQUEST && resultCode == RESULT_OK) {
            // Проверяем, содержит ли результат маленькую картинку
            if (data != null) {
                if (data.hasExtra("data")) {
                    Bitmap thumbnailBitmap = data.getParcelableExtra("data");
                    // Какие-то действия с миниатюрой
                    imageView.setImageBitmap(thumbnailBitmap);
                }
            } else {
                // Какие-то действия с полноценным изображением,
                // сохраненным по адресу outputFileUri
                imageView.setImageURI(outputFileUri);
            }
        }
    }

    private void getThumbnailPicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST);
    }

    private void saveFullImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "test.jpg");
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST);
    }
}




//    private static final int CAMERA_REQUEST = 0;

//    // Щелчок кнопки
//    public void onClick(View v) {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//        // Длинный вариант
//        // Intent cameraIntent = new Intent();
//        // cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        // startActivityForResult(cameraIntent, CAMERA_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
//            // Фотка сделана, извлекаем картинку
//            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(thumbnailBitmap);
//        }
//    }




