package com.example.minorproject.AfterSelectedCategories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
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


        database.getReference("CollegeEvent").child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot s : snapshot.getChildren()) {

                    arrayList.add(new CategoriesEvent(s.child("evtTitle").getValue(String.class),s.child("evtDepartment").getValue(String.class),s.child("evtTime").getValue(String.class)));
                }
                categoriesRecycleView.notifyDataSetChanged();
                binding.CategoryRecycle.setAdapter(categoriesRecycleView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AfterSelectedActitvity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });






    }
}