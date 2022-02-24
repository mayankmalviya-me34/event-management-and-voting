package com.example.minorproject.loginandregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForStudent;
import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.R;
import com.example.minorproject.databinding.FragmentStudentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class StudentRegister extends Fragment {
    TextView loginStd;
    Button registerStd;

    FirebaseAuth auth;
    FragmentStudentRegisterBinding binding;
    FirebaseDatabase database;





    public StudentRegister() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //firebase...
        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();


        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account....");
        progressDialog.setMessage("We are creating your account....");
        progressDialog.dismiss();



         binding.LoginStudent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent  = new Intent(getContext(),StudentLoginActivity.class);
                 startActivity(intent);
             }
         });
         binding.StudentRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 progressDialog.show();


                 auth.createUserWithEmailAndPassword(binding.StdEmail.getText().toString(),binding.StdPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             String StduserId = task.getResult().getUser().getUid();
                             StudentModel studentModel = new StudentModel(binding.StdUserName.getText().toString(),binding.StdEnrollmentNumber.getText().toString(),binding.StdEmail.getText().toString(),binding.StdPassword.getText().toString(),StduserId);
                             database.getReference().child("StudentData").child(StduserId).setValue(studentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                     }
                                    else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                 }
                             });
                             Intent intent = new Intent(getContext(), HomePageActivityForStudent.class);
                             startActivity(intent);
                             progressDialog.dismiss();




                         }
                         else{

                         }
                     }
                 });
             }
         });



        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}