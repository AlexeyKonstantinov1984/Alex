package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity
    {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        // в методе onCreate()

    }

public void onloginClick(View view)
{
   Intent intent = new Intent (login.this, MainActivity.class) ;
   startActivity(intent);
}




}
