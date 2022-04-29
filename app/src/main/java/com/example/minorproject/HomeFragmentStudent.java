package com.example.minorproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.AfterSelectedCategories.CategoriesRecycleView;
import com.example.minorproject.HomePageFacultyAndStudent.ui.LiveEvent.liveEventAdapter;
import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.NewsAndNotice.NewsAdapter;
import com.example.minorproject.databinding.FragmentHomeStudentBinding;
import com.example.minorproject.databinding.FragmentLangBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragmentStudent extends Fragment {

   FragmentHomeStudentBinding binding;
   ArrayList<CategoriesEvent> arrayList = new ArrayList<>();
    ArrayList<CategoriesEvent> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


   FirebaseAuth auth;

   FirebaseDatabase database;
    String[] catgArr = {"sports","entertainment","club","cdc"};

    public HomeFragmentStudent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_home_student, container, false);
        binding = FragmentHomeStudentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // firebase...
        database  = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        // student participation event.........start...
        binding.studentHomeRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));
        CategoriesRecycleView categoriesRecycleView = new CategoriesRecycleView(arrayList,getContext());

        for(int a=0; a< catgArr.length; a++){

            database.getReference("CollegeEvent").child("Categories").child(catgArr[a]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot snap : snapshot.getChildren()) {

                       String eventID = snap.child("currentDateTime").getValue(String.class);
                       String catg = snap.child("evtCatg").getValue(String.class);
                       database.getReference("ParticipatedStudentList").child(eventID).addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               arrayList.clear();
                               for (DataSnapshot snap: snapshot.getChildren()) {
                                   if(auth.getUid().contains(snap.child("userID").getValue(String.class))){
                                       database.getReference("CollegeEvent").child("Categories").child(catg).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {

                                               for (DataSnapshot s:snapshot.getChildren()) {
                                                   if(eventID.contains(s.child("currentDateTime").getValue(String.class))){
                                                       arrayList.add(new CategoriesEvent(s.child("evtPhoto").getValue(String.class),s.child("evtTitle").getValue(String.class),s.child("evtDescription").getValue(String.class), s.child("evtDepartment").getValue(String.class),s.child("evtOrganizerName").getValue(String.class),s.child("evtOrganizerNumber").getValue(String.class),s.child("evtAddress").getValue(String.class),s.child("evtDate").getValue(String.class),s.child("evtTime").getValue(String.class),s.child("currentDateTime").getValue(String.class),s.child("facultyID").getValue(String.class),s.child("lastDate").getValue(String.class), s.child("eligibility").getValue(String.class),s.child("isLive").getValue(String.class),s.child("evtCatg").getValue(String.class)));
                                                   }
                                               }

                                               binding.studentHomeRV.setAdapter(categoriesRecycleView);


                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {

                                           }
                                       });
                                       categoriesRecycleView.notifyDataSetChanged();

                                   }

                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });


                    }


                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });

        }
        // .............finish



        //Live event ........start....
        binding.studentHomeLiveEventRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));

        liveEventAdapter adapter1 = new liveEventAdapter(arrayList1,getContext());

        for(int a=0; a< catgArr.length; a++){
            arrayList1.clear();
            database.getReference("CollegeEvent").child("Categories").child(catgArr[a]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot s : snapshot.getChildren()) {
                        if(s.child("isLive").getValue(String.class).contains("1")){
                            //Toast.makeText(getContext(),"working",Toast.LENGTH_LONG).show();

                            arrayList1.add(new CategoriesEvent(s.child("evtPhoto").getValue(String.class),s.child("evtTitle").getValue(String.class),s.child("evtDescription").getValue(String.class), s.child("evtDepartment").getValue(String.class),s.child("evtOrganizerName").getValue(String.class),s.child("evtOrganizerNumber").getValue(String.class),s.child("evtAddress").getValue(String.class),s.child("evtDate").getValue(String.class),s.child("evtTime").getValue(String.class),s.child("currentDateTime").getValue(String.class),s.child("facultyID").getValue(String.class),s.child("lastDate").getValue(String.class), s.child("eligibility").getValue(String.class),s.child("isLive").getValue(String.class),s.child("evtCatg").getValue(String.class)));


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
            binding.studentHomeLiveEventRV.setAdapter(adapter1);


        }
        // finish..........


        // news.....
arrayList3.add("hi");
arrayList3.add("hi");
arrayList3.add("hi");

        NewsAdapter newsAdapter = new NewsAdapter(arrayList3,getContext());
        binding.studentHomeNewsRV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        binding.studentHomeNewsRV.setAdapter(newsAdapter);
        binding.pageIndicatorView.setSelection(currentPage);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                int NUM_PAGES = arrayList3.size();
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                    binding.pageIndicatorView.setSelection(currentPage);

                }
                binding.studentHomeNewsRV.smoothScrollToPosition(currentPage++);
                binding.pageIndicatorView.setSelection(currentPage);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);



        return root;
    }
}