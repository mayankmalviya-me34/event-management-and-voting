package com.example.minorproject.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.databinding.ActivityStudentLoginBinding;

public class StudentLoginActivity extends AppCompatActivity {
    ActivityStudentLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLoginActivity.this,RegisterActivitySF.class);
                startActivity(intent);
            }
        });
    }
}