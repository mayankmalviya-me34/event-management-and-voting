package com.example.minorproject.loginandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.HomePageFacultyAndStudent.HomePageActivityForFaculty;
import com.example.minorproject.databinding.ActivityFacultyLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyLoginActivity extends AppCompatActivity {
  ActivityFacultyLoginBinding binding;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        ProgressDialog progressDialog = new ProgressDialog(FacultyLoginActivity.this);
        progressDialog.setTitle("Longing in Account....");
        progressDialog.setMessage("We are fetching your account details....");

        binding.backToRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyLoginActivity.this,RegisterActivitySF.class);
                startActivity(intent);
            }
        });

        binding.fctLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.fctEmaillogin.getText().toString(),binding.fctPasswordlogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(FacultyLoginActivity.this, HomePageActivityForFaculty.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                        }
                        else{

                            Snackbar.make(v,task.getException().getMessage(),Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
