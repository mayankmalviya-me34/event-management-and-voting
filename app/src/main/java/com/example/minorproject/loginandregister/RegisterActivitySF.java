package com.example.minorproject.loginandregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityRegisterSfBinding;

public class RegisterActivitySF extends AppCompatActivity {
    ActivityRegisterSfBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterSfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        StudentRegister fragment = new StudentRegister();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
        binding.Std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentRegister fragment = new StudentRegister();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment);
                transaction.commit();
            }
        });
        binding.FAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FacultyRegister fragment = new FacultyRegister();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment);
                transaction.commit();
            }
        });


    }
}