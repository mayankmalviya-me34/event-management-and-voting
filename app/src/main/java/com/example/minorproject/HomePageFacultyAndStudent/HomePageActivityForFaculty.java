package com.example.minorproject.HomePageFacultyAndStudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.minorproject.CreateEventFragment;
import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityHomePageForFacultyBinding;
import com.example.minorproject.loginandregister.FacultyRegister;

public class HomePageActivityForFaculty extends AppCompatActivity {
    ActivityHomePageForFacultyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageForFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.FacultyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacultyProfile fragment = new FacultyProfile();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.LinearFac,fragment);
                transaction.commit();
            }
        });
        binding.CreatEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEventFragment fragment = new CreateEventFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.LinearFac,fragment);
                transaction.commit();
            }
        });

    }
}