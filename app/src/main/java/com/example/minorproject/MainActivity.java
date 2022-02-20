package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.minorproject.databinding.ActivityMainBinding;
import com.example.minorproject.loginandregister.RegisterActivitySF;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Animation animationt,animationb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anthem);
//        binding.VideoView.setVideoURI(uri);
//        binding.VideoView.start();

        animationt = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        animationb = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        binding.imageViewAcroN.setAnimation(animationt);
        binding.startButton.setAnimation(animationb);
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivitySF.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        binding.VideoView.pause();
//    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        binding.VideoView.resume();
//    }
}