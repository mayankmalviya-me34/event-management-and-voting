package com.example.minorproject.loginandregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForStudent;
import com.example.minorproject.R;


public class StudentRegister extends Fragment {
    TextView loginStd;
    Button registerStd;



    public StudentRegister() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account....");
        progressDialog.setMessage("We are creating your account....");
        progressDialog.dismiss();
        View view = inflater.inflate(R.layout.fragment_student_register, container, false);

        loginStd=view.findViewById(R.id.LoginStudent);
        registerStd = view.findViewById(R.id.StudentRegister);
         loginStd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent  = new Intent(getContext(),StudentLoginActivity.class);
                 startActivity(intent);
             }
         });
         registerStd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(), HomePageActivityForStudent.class);
                 startActivity(intent);
                 progressDialog.show();
             }
         });



        return view;
    }
}