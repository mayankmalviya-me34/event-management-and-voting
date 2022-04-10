package com.example.minorproject.HomePageFacultyAndStudent.ui.LiveEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.AfterSelectedCategories.CategoriesRecycleView;
import com.example.minorproject.databinding.FragmentLiveEventBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LiveEventFragment extends Fragment {
    FirebaseDatabase database;
    ArrayList<CategoriesEvent> arrayList = new ArrayList<>();

    private FragmentLiveEventBinding binding;
    String[] catgArr = {"sports","entertainment","club","cdc"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentLiveEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //
        database = FirebaseDatabase.getInstance();
        binding.liveEventRV.setLayoutManager(new GridLayoutManager(getContext(),2));
        liveEventAdapter adapter1 = new liveEventAdapter(arrayList,getContext());


        for(int a=0; a< catgArr.length; a++){
            arrayList.clear();
            database.getReference("CollegeEvent").child("Categories").child(catgArr[a]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot s : snapshot.getChildren()) {
                        if(s.child("isLive").getValue(String.class).contains("1")){
                            //Toast.makeText(getContext(),"working",Toast.LENGTH_LONG).show();

                            arrayList.add(new CategoriesEvent(s.child("evtPhoto").getValue(String.class),s.child("evtTitle").getValue(String.class),s.child("evtDescription").getValue(String.class), s.child("evtDepartment").getValue(String.class),s.child("evtOrganizerName").getValue(String.class),s.child("evtOrganizerNumber").getValue(String.class),s.child("evtAddress").getValue(String.class),s.child("evtDate").getValue(String.class),s.child("evtTime").getValue(String.class),s.child("currentDateTime").getValue(String.class),s.child("facultyID").getValue(String.class),s.child("lastDate").getValue(String.class), s.child("eligibility").getValue(String.class),s.child("isLive").getValue(String.class),s.child("evtCatg").getValue(String.class)));

                        }
                    }
                    adapter1.notifyDataSetChanged();

//                binding.progressbar.setVisibility(View.GONE);
//                binding.CategoryRecycle.setVisibility(View.VISIBLE);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

            binding.liveEventRV.setAdapter(adapter1);
        }





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}