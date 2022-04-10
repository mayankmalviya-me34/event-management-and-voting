package com.example.minorproject.AfterSelectedCategories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityAfterSelectedActitvityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AfterSelectedActitvity extends AppCompatActivity {
    ActivityAfterSelectedActitvityBinding binding;
    ArrayList<CategoriesEvent> arrayList = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAfterSelectedActitvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        database = FirebaseDatabase.getInstance();
        binding.CategoryRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        CategoriesRecycleView categoriesRecycleView = new CategoriesRecycleView(arrayList,this);



        database.getReference("CollegeEvent").child("Categories").child(getIntent().getStringExtra("catg")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot s : snapshot.getChildren()) {

                    arrayList.add(new CategoriesEvent(s.child("evtPhoto").getValue(String.class),s.child("evtTitle").getValue(String.class),s.child("evtDescription").getValue(String.class), s.child("evtDepartment").getValue(String.class),s.child("evtOrganizerName").getValue(String.class),s.child("evtOrganizerNumber").getValue(String.class),s.child("evtAddress").getValue(String.class),s.child("evtDate").getValue(String.class),s.child("evtTime").getValue(String.class),s.child("currentDateTime").getValue(String.class),s.child("facultyID").getValue(String.class),s.child("lastDate").getValue(String.class), s.child("eligibility").getValue(String.class),s.child("isLive").getValue(String.class),s.child("evtCatg").getValue(String.class)));
                }
                categoriesRecycleView.notifyDataSetChanged();
                binding.CategoryRecycle.setAdapter(categoriesRecycleView);
                binding.progressbar.setVisibility(View.GONE);
                binding.CategoryRecycle.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AfterSelectedActitvity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });







    }
}