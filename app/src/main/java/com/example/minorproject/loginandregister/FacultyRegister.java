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
    FirebaseAuth auth;
    FragmentFacultyRegisterBinding binding;
    FirebaseDatabase database;

    public FacultyRegister() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFacultyRegisterBinding.inflate(inflater,container,false);
        View view =  binding.getRoot();

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();


        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account....");
        progressDialog.setMessage("We are creating your account....");
        progressDialog.dismiss();

        binding.loginFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(),FacultyLoginActivity.class);
                startActivity(intent);
            }
        });
        binding.FacultyRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();


                auth.createUserWithEmailAndPassword(binding.fctEmailId.getText().toString(),binding.fctpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String fctuserId = task.getResult().getUser().getUid();
                           FacultyModel facultyModel = new FacultyModel(binding.fctEmailId.getText().toString(),binding.fctpassword.getText().toString(),binding.fctUserName.getText().toString());
                            database.getReference().child("FacultyData").child(fctuserId).setValue(facultyModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), HomePageActivityForFaculty.class);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });





                        }
                        else{

                        }
                    }
                });
            }
        });

//        loginFac = view.findViewById(R.id.LoginFaculty);
//        registerFac = view.findViewById(R.id.FacultyRegisterbtn);
//        pass = view.findViewById(R.id.fctpassword);
//        loginFac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent  = new Intent(getContext(),FacultyLoginActivity.class);
//                startActivity(intent);
//            }
//        });
//        registerFac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    Intent intent = new Intent(getContext(), HomePageActivityForFaculty.class);
//                    startActivity(intent);
//
//
//
//
//            }
//        });
        return  view;
    }
}
