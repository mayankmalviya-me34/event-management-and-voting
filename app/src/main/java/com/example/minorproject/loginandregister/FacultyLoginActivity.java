package com.example.minorproject.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.databinding.ActivityFacultyLoginBinding;

public class FacultyLoginActivity extends AppCompatActivity {
    ActivityFacultyLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backToRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyLoginActivity.this,RegisterActivitySF.class);
                startActivity(intent);
            }
        });

    }
}