package com.example.minorproject.ParticipatedStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.ParticipatedStudent.Aapter.StudentListAdapter;
import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityParticipatedStudentDetailsAndListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParticipatedStudentDetailsAndList extends AppCompatActivity {
    ActivityParticipatedStudentDetailsAndListBinding binding;
    ArrayList<StudentModel> arrayList = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParticipatedStudentDetailsAndListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //firebase...
        database  = FirebaseDatabase.getInstance();

        Intent intent = getIntent();
        String evtDT_ID = intent.getStringExtra("evtID");


        binding.studentParticipationListRV.setLayoutManager(new LinearLayoutManager(this));
        database.getReference("ParticipatedStudentList").child(evtDT_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot snap: snapshot.getChildren()) {

                    arrayList.add(new StudentModel(snap.child("name").getValue(String.class),snap.child("enroll").getValue(String.class),snap.child("userID").getValue(String.class),snap.child("participateTime").getValue(String.class),snap.child("eventID").getValue(String.class)));

                }
                StudentListAdapter adapter = new StudentListAdapter(ParticipatedStudentDetailsAndList.this,arrayList);
                binding.studentParticipationListRV.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}