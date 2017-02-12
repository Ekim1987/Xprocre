package com.example.user.xprocure;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.telly.mrvector.MrVector;

public class VectorTestActivity extends ActionBarActivity {

    Toolbar toolBar;
    ImageView imageView;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_test);
        toolBar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);

        imageView=(ImageView)findViewById(R.id.vectorImage);

        Drawable drawerable =null;
        drawerable=MrVector.inflate(getResources(),R.drawable.vector_clock);
        if(Util.isJellyBeanOrGreater()){
            imageView.setBackground(drawerable);
        }else{
            imageView.setBackgroundDrawable(drawerable);
        }
    }
}
