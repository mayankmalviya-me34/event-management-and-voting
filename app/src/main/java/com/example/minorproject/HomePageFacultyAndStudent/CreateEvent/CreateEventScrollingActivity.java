package com.example.minorproject.HomePageFacultyAndStudent.CreateEvent;


import android.net.Uri;
import android.os.Bundle;

import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityCreateEventScrollingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateEventScrollingActivity extends AppCompatActivity {

    private ActivityCreateEventScrollingBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ActivityResultLauncher<String> launcher;
    FirebaseStorage storage;

    String dept="other",catgy="other",imageEVT="https://thumbs.dreamstime.com/z/event-planning-working-desk-notebook-events-word-computer-pencil-notepad-clock-concept-98612010.jpg";
    RadioButton cst,it,cs,me,cv;
    RadioButton sports,cdc,club,entertainment;
    EditText evtName,evtDesp,evtOrgName,evtOrgNumber,evtAddress,evtDate,evtTime,evtWCP,evtLastDate;
    Button evtCreateBtn;
    ProgressBar PBHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateEventScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        //
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        //hooks
        cst = findViewById(R.id.radio_CST);
        it = findViewById(R.id.radio_IT);
        cs = findViewById(R.id.radio_CS);
        me = findViewById(R.id.radio_ME);
        cv = findViewById(R.id.radio_CV);

        //hooks
        sports  = findViewById(R.id.radio_Sports);
        cdc  = findViewById(R.id.radio_CDC);
        club  = findViewById(R.id.radio_Club);
        entertainment = findViewById(R.id.radio_Entertainment);

        //hooks
        evtName = findViewById(R.id.editTextTextEventName);
        evtDesp = findViewById(R.id.editTextTextDescription);
        evtOrgName = findViewById(R.id.editTextEventOrganizerName);
        evtOrgNumber = findViewById(R.id.editTextEventOrganizerContactNumber);
        evtAddress = findViewById(R.id.editTextTextAddress);
        evtDate = findViewById(R.id.editTextTextDate);
        evtTime = findViewById(R.id.editTextTextTime);
        evtCreateBtn = findViewById(R.id.EventCreateButton);
        PBHorizontal = findViewById(R.id.progressBarHorizontal);
        evtLastDate = findViewById(R.id.editTextTextLastDate);
        evtWCP = findViewById(R.id.editTextTextWhoCanParti);


//        Picasso.get()
//                .load("https://dcassetcdn.com/design_img/1074833/209838/209838_5779143_1074833_image.jpg")
//                .resize(50, 50)
//                .centerCrop()
//                .into(binding.EventPhoto);







        cst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cst.getText().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = it.getText().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cs.getText().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cv.getText().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = me.getText().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = sports.getTag().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        cdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = cdc.getTag().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = club.getTag().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = entertainment.getTag().toString();
                Toast.makeText(CreateEventScrollingActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.EventPhoto.setImageURI(result);

                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss", Locale.getDefault());
                String currentDateandTime1 = sdf1.format(new Date());


                 StorageReference reference  = storage.getReference().child("Image").child(currentDateandTime1);
                reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageEVT = uri.toString();
                                Toast.makeText(CreateEventScrollingActivity.this, "Done"+imageEVT+uri, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

            }
        });
        FloatingActionButton fab = binding.setPhoto;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");

            }
        });

        evtCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PBHorizontal.setVisibility(View.VISIBLE);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                Toast.makeText(CreateEventScrollingActivity.this, ""+currentDateandTime, Toast.LENGTH_LONG).show();
                CategoriesEvent categoriesEvent = new CategoriesEvent(imageEVT,
                        evtName.getText().toString(),evtDesp.getText().toString(),dept,evtOrgName.getText().toString(),evtOrgNumber.getText().toString(),evtAddress.getText().toString(),
                        evtDate.getText().toString(),evtTime.getText().toString(),currentDateandTime, auth.getUid(), evtLastDate.getText().toString(), evtWCP.getText().toString(), "0",catgy);

                database.getReference().child("CollegeEvent").child("Categories").child(catgy).child(currentDateandTime).setValue(categoriesEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Snackbar.make(v, "Event Created successfully", Snackbar.LENGTH_SHORT).show();
                            PBHorizontal.setVisibility(View.GONE);
                            finish();
                        }
                    }
                });
            }
        });
       


    }

}
