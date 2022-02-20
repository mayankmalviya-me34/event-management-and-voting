package com.example.minorproject.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityRegisterSfBinding;

public class RegisterActivitySF extends AppCompatActivity {
    ActivityRegisterSfBinding binding;
    Animation animationt,animationb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterSfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        animationt = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        animationb = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        //
        binding.cardView.setAnimation(animationb);
        binding.Std.setAnimation(animationt);
        binding.FAC.setAnimation(animationt);
        binding.imageViewAcro.setAnimation(animationt);

        StudentRegister fragment = new StudentRegister();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
        binding.Std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.cardView.setAnimation(animationb);
                StudentRegister fragment = new StudentRegister();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment);
                transaction.commit();
            }
        });
        binding.FAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cardView.setAnimation(animationb);
                FacultyRegister fragment = new FacultyRegister();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment);
                transaction.commit();
            }
        });



    }
}