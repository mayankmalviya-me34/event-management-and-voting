package com.example.minorproject;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.loginandregister.StudentLoginActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class HomeFragmentStudent extends Fragment {

 ImageView menuStd,cancel_menu,menurofilepic;
 FirebaseDatabase database;
 FirebaseAuth auth;
 TextView menuStdName,menuEmailId,setting,contactUs,logout,allEvents;
 private RadioGroup grpRadio;

    public HomeFragmentStudent() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home_student, container, false);

        menuStd = view.findViewById(R.id.menuStd);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.menu_student_layout);

        menuStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"opening menu..",Snackbar.LENGTH_LONG).show();

                dialog.setCancelable(true);

                dialog.show();

                database.getReference("StudentInfoDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snap) {


                        String name =  snap.child(auth.getUid()).child("name").getValue(String.class).toUpperCase();
                        //   Toast.makeText(getContext(), snap.child(auth.getUid()).child("name").getValue(String.class).toUpperCase(), Toast.LENGTH_SHORT).show();
                        menuStdName.setText(""+name);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "not Responding...", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });


        cancel_menu = dialog.findViewById(R.id.cancelMenu_std);
        menurofilepic = dialog.findViewById(R.id.CurrentStdMenuPic);
        menuStdName = dialog.findViewById(R.id.CurrentFacMenuName);
        menuEmailId = dialog.findViewById(R.id.CurrentStdMenuEmail);
        setting = dialog.findViewById(R.id.menu_setting);
        contactUs = dialog.findViewById(R.id.ContactUsButton);
        logout = dialog.findViewById(R.id.LogoutButton);
        allEvents = dialog.findViewById(R.id.all_event);
/*
        database.getReference("StudentProfilePicture").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).placeholder(R.drawable.ic_baseline_portrait_24).into(menurofilepic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/

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
          startActivity(new Intent(getActivity(), StudentLoginActivity.class));
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
/*
        grpRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn =  (RadioButton)group.findViewById(checkedId);

                switch(checkedId)
                {
                    case R.id.DarkRadioBtn:
//                        AppCompatDelegate
//                                .setDefaultNightMode(
//                                        AppCompatDelegate
//                                                .MODE_NIGHT_YES);
                        Toast.makeText(getContext(), "Apply dark theme", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.lightRadioBtn:

                        break;
                }


            }
        });

*/

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return view;
    }
}
