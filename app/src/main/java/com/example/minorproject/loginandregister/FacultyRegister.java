package com.example.minorproject.loginandregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForFaculty;
import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForStudent;
import com.example.minorproject.R;

import java.util.function.ObjIntConsumer;


public class FacultyRegister extends Fragment {
TextView loginFac;
Button registerFac;
EditText pass;

    public FacultyRegister() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty_register, container, false);
        loginFac = view.findViewById(R.id.LoginFaculty);
        registerFac = view.findViewById(R.id.FacultyRegister);
        pass = view.findViewById(R.id.editTextTextPasswordFac);
        loginFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(),FacultyLoginActivity.class);
                startActivity(intent);
            }
        });
        registerFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getContext(), HomePageActivityForFaculty.class);
                    startActivity(intent);




            }
        });
        return  view;
    }
}