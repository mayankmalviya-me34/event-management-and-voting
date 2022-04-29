package com.example.minorproject.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForStudent;
import com.example.minorproject.databinding.ActivityStudentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentLoginActivity extends AppCompatActivity {
    ActivityStudentLoginBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Firebase.....
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        ProgressDialog progressDialog = new ProgressDialog(StudentLoginActivity.this);
        progressDialog.setTitle("Login in your Account....");
        progressDialog.setMessage("We are fetching your account details....");

        binding.backToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLoginActivity.this,RegisterActivitySF.class);
                startActivity(intent);
            }
        });
        binding.StdLoginAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.StdEmailLogin.getText().toString(),binding.StdPasswordLogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(StudentLoginActivity.this, HomePageActivityForStudent.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                        }
                        else{
                            Snackbar.make(v,"Please check your internet connection",Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}