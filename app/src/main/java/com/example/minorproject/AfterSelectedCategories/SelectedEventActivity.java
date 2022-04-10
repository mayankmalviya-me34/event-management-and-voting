package com.example.minorproject.AfterSelectedCategories;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;


import com.example.minorproject.databinding.ActivitySelectedEventBinding;
import com.squareup.picasso.Picasso;

public class SelectedEventActivity extends AppCompatActivity {
    ActivitySelectedEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectedEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Picasso.get().load(getIntent().getStringExtra("photo")).into(binding.eventPhoto);

        binding.eventName.setText(getIntent().getStringExtra("name"));
        binding.eventDescription.setText(getIntent().getStringExtra("description"));
        binding.eventDepart.setText(getIntent().getStringExtra("dept"));
        binding.eventOrgName.setText(getIntent().getStringExtra("org"));
        binding.eventOrgContactNo.setText(getIntent().getStringExtra("orgPh"));
        binding.eventAddress.setText(getIntent().getStringExtra("address"));
        binding.eventDate.setText(getIntent().getStringExtra("date"));
        binding.eventTime.setText(getIntent().getStringExtra("time"));



    }
}