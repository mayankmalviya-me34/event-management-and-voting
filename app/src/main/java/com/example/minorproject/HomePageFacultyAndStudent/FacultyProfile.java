package com.example.minorproject.HomePageFacultyAndStudent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.minorproject.R;

public class FacultyProfile extends Fragment {
 private Button facultylogout;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public FacultyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty_profile, container, false);
        Toast.makeText(getContext(), "ready", Toast.LENGTH_SHORT).show();

        facultylogout = view.findViewById(R.id.fctlogoutbtn);
        facultylogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sign out", Toast.LENGTH_SHORT).show();
                auth.signOut();
                startActivity(new Intent(getContext(), StudentLoginActivity.class));

            }
        });
        return view;
    }
}
