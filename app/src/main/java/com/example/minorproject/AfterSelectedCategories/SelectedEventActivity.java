package com.example.minorproject.AfterSelectedCategories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.ParticipatedStudent.Aapter.StudentListAdapter;
import com.example.minorproject.ParticipatedStudent.ParticipatedStudentDetailsAndList;
import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivitySelectedEventBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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



        database.getReference("ParticipatedStudentList").child(getIntent().getStringExtra("eventID")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap: snapshot.getChildren()) {

                    if(auth.getUid().contains(snap.child("userID").getValue(String.class))){
                        binding.participateBtnForStud.setText("Already participated");
                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        binding.participateBtnForStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("StudentData").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue(String.class);
                        String enroll = snapshot.child("enroll").getValue(String.class);
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss a", Locale.getDefault());
                        String currentDateandTimeNow = sdf1.format(new Date());



                        database.getReference().child("ParticipatedStudentList").child(getIntent().getStringExtra("eventID")).child(auth.getUid()).setValue(new StudentModel(name,enroll,auth.getUid(),currentDateandTimeNow,getIntent().getStringExtra("eventID"))).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Dialog dialogDone = new Dialog(SelectedEventActivity.this);
                                    dialogDone.setContentView(R.layout.partiipation_done_dialog_box);
                                    dialogDone.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialogDone.show();

                                }
                                else {
                                    Toast.makeText(SelectedEventActivity.this, "Try again...there may be some issue", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        });



    }
}