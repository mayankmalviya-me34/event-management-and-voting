package com.example.minorproject.loginandregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minorproject.R;


public class FacultyRegister extends Fragment {
TextView loginFac;

    public FacultyRegister() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty_register, container, false);
        loginFac = view.findViewById(R.id.LoginFaculty);
        loginFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(),FacultyLoginActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }
}