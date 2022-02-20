package com.example.minorproject.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityOnTapNotoficationBinding;
import com.google.android.material.snackbar.Snackbar;

public class OnTapNotoficationActivity extends AppCompatActivity {

    ActivityOnTapNotoficationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnTapNotoficationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OnTapNotoficationActivity.this,"Successfully participate",Toast.LENGTH_LONG).show();
            }
        });





    }
}