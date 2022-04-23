package com.example.minorproject.AfterSelectedCategories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.minorproject.databinding.ActivitySelectedEventBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SelectedEventActivity extends AppCompatActivity {
    ActivitySelectedEventBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectedEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // firebase ...
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        Picasso.get().load(getIntent().getStringExtra("photo")).into(binding.eventPhoto);

        binding.eventName.setText(getIntent().getStringExtra("name"));
        binding.eventDescription.setText(getIntent().getStringExtra("description"));
        binding.eventDepart.setText(getIntent().getStringExtra("dept"));
        binding.eventOrgName.setText(getIntent().getStringExtra("org"));
        binding.eventOrgContactNo.setText(getIntent().getStringExtra("orgPh"));
        binding.eventAddress.setText(getIntent().getStringExtra("address"));
        binding.eventDate.setText(getIntent().getStringExtra("date"));
        binding.eventTime.setText(getIntent().getStringExtra("time"));

        binding.participateBtnForStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database.getReference().child("ParticipatedStudentList").child(getIntent().getStringExtra("eventID")).child(auth.getUid());//.addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(SelectedEventActivity.this, "successfully participated", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            Toast.makeText(SelectedEventActivity.this, "please try again....", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

            }
        });



    }
}