package com.example.minorproject.HomePageFacultyAndStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.minorproject.databinding.ActivityHomePageBinding;


public class HomePageActivityForStudent extends AppCompatActivity {
    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}