package com.example.minorproject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.AfterSelectedCategories.CategoriesRecycleView;
import com.example.minorproject.HomePageFacultyAndStudent.ui.LiveEvent.liveEventAdapter;
import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.NewsAndNotice.NewsAdapter;
import com.example.minorproject.databinding.FragmentHomeStudentBinding;
import com.example.minorproject.databinding.FragmentLangBinding;
import com.example.minorproject.loginandregister.StudentLoginActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
    ImageView menuStd,cancel_menu,menuProfilepic;

    TextView menuStdName,menuEmailId,setting,contact,logout,allEvents,notifi;
    private RadioGroup grpRadio,notigrpRadio;

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


        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.menu_student_layout);

        binding.menuFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"opening menu..",Snackbar.LENGTH_LONG).show();

                dialog.setCancelable(true);

                dialog.show();



            }
        });



        cancel_menu = dialog.findViewById(R.id.cancelMenu_std);

        menuStdName = (TextView)dialog.findViewById(R.id.CurrentStdMenuName);
        menuEmailId = (TextView)dialog.findViewById(R.id.CurrentStdMenuEmail);
        setting = (TextView)dialog.findViewById(R.id.menu_setting);
        contact = (TextView) dialog.findViewById(R.id.menu_contact);
        logout = (TextView)dialog.findViewById(R.id.LogoutButton);
        notifi = (TextView)dialog.findViewById(R.id.menu_notifications);
        allEvents = (TextView)dialog.findViewById(R.id.all_event);

allEvents.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
        Toast.makeText(getContext(), "here is Your Events...", Toast.LENGTH_SHORT).show();
    }
});
        database.getReference("StudentInfoDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {



                //    Toast.makeText(getContext(), snap.child(auth.getUid()).child("name").getValue(String.class).toUpperCase(), Toast.LENGTH_SHORT).show();
                menuStdName.setText(snap.child(auth.getUid()).child("name").getValue(String.class).toUpperCase());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "not Responding...", Toast.LENGTH_SHORT).show();

            }
        });

        database.getReference("StudentProfilePicture").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String link = snapshot.getValue(String.class);

                    menuProfilepic = dialog.findViewById(R.id.CurrentStdMenuPic);
                    Picasso.get().load(link).into(menuProfilepic);
                    Toast.makeText(getContext(), "set Image Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        menuEmailId.setText(auth.getCurrentUser().getEmail());
        cancel_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Snackbar.make(v,"Loging Out...",Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), StudentLoginActivity.class));
            }
        });
        Dialog dialog1 = new Dialog(getContext());
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog1.setContentView(R.layout.setting_menu);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog1.show();
                dialog1.setCanceledOnTouchOutside(true);
            }
        });

        Dialog dialog2 = new Dialog(getContext());
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        dialog2.setContentView(R.layout.contact_us_layout);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "contact us..", Toast.LENGTH_SHORT).show();
                dialog2.show();
                dialog2.setCanceledOnTouchOutside(true);
            }
        });
        grpRadio = (RadioGroup)dialog1.findViewById(R.id.grpRadio);
        grpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch(checkedId)
                {
                    case R.id.DarkRadioBtn:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        Toast.makeText(getContext(), "Apply dark theme", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.lightRadioBtn:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        Toast.makeText(getContext(), "Apply light theme", Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        });
        Dialog dialog3 = new Dialog(getContext());
        dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        dialog2.setContentView(R.layout.notification_menu);

       notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "contact us..", Toast.LENGTH_SHORT).show();
                dialog3.show();
                dialog3.setCanceledOnTouchOutside(true);
            }
        });

        notigrpRadio = (RadioGroup)dialog3.findViewById(R.id.notyfygrp);
//        notigrpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//
//                switch(checkedId)
//                {
//                    case R.id.oNnotyfyRadioBtn:
//
//                        Toast.makeText(getContext(), "Notification On..", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    case R.id.oFFnotyfyRadioBtn:
//
//                        Toast.makeText(getContext(), "Notification OFF.", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//
//            }
//        });

        return root;
    }
}
