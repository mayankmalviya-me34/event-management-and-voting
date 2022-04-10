package com.example.minorproject.FacultyEventRelated;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.HomePageFacultyAndStudent.CreateEvent.CreateEventScrollingActivity;
import com.example.minorproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditAndUpdateEventActivity extends AppCompatActivity {
    EditText eventUpadtedName,eventUpadtedDes,eventUpadtedTime,eventUpadtedDate,eventUpadtedAdd,eventUpadtedOname,eventUpadtedOnum,eventUpadtedLD,eventUpadtedWCP;
    RadioButton cst,it,cs,me,cv;
    RadioButton sports,cdc,club,entertainment;
    String dept="other",catgy="other",islive,imageEVT;
    Button eventUpdateBtn;
    ProgressBar PBHorizontal;
    String currentDateandTime = "";

    ActivityResultLauncher<String> launcher;

    //
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;

    ImageView eventUpdatedPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_scrolling);
        //
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        eventUpadtedName = findViewById(R.id.editTextTextEventName);
        eventUpadtedDes = findViewById(R.id.editTextTextDescription);
        eventUpadtedTime = findViewById(R.id.editTextTextTime);
        eventUpadtedDate = findViewById(R.id.editTextTextDate);
        eventUpadtedAdd = findViewById(R.id.editTextTextAddress);
        eventUpadtedOname = findViewById(R.id.editTextEventOrganizerName);
        eventUpadtedOnum = findViewById(R.id.editTextEventOrganizerContactNumber);
        eventUpadtedLD = findViewById(R.id.editTextTextLastDate);
        eventUpadtedWCP = findViewById(R.id.editTextTextWhoCanParti);
        eventUpdatedPic = findViewById(R.id.EventPhoto);

        //hooks
        eventUpdateBtn = findViewById(R.id.EventCreateButton);

        PBHorizontal = findViewById(R.id.progressBarHorizontal);

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

        //
        Intent getEventDetails = getIntent();

        // setting up the event details:-
        eventUpadtedName.setText(getEventDetails.getStringExtra("name"));
        eventUpadtedDes.setText(getEventDetails.getStringExtra("des"));
        eventUpadtedDate.setText(getEventDetails.getStringExtra("date"));
        eventUpadtedTime.setText(getEventDetails.getStringExtra("time"));
        eventUpadtedOname.setText(getEventDetails.getStringExtra("Oname"));
        eventUpadtedOnum.setText(getEventDetails.getStringExtra("Onum"));
        eventUpadtedWCP.setText(getEventDetails.getStringExtra("WCP"));
        eventUpadtedAdd.setText(getEventDetails.getStringExtra("add"));
        eventUpadtedLD.setText(getEventDetails.getStringExtra("LD"));
        Picasso.get().load(getEventDetails.getStringExtra("pic")).into(eventUpdatedPic);
        catgy = getEventDetails.getStringExtra("catg");
        dept = getEventDetails.getStringExtra("dept");
        currentDateandTime = getEventDetails.getStringExtra("DT");
        islive = getEventDetails.getStringExtra("Live");

        //changing btn text...
        eventUpdateBtn.setText("Update");


        //checking condition ....dept
        if(cst.getText().toString().contains(dept)){
            cst.setChecked(true);
        }else if (it.getText().toString().contains(dept)){
            it.setChecked(true);
        }
        else if (cs.getText().toString().contains(dept)){
            cs.setChecked(true);
        }
        else if (me.getText().toString().contains(dept)){
            me.setChecked(true);
        }else if (cv.getText().toString().contains(dept)){
            cv.setChecked(true);
        }

        //checking condition....catg

        if(sports.getTag().toString().contains(catgy)){
            sports.setChecked(true);
        }else if (cdc.getTag().toString().contains(catgy)){
            cdc.setChecked(true);
        }
        else if (club.getTag().toString().contains(catgy)){
            club.setChecked(true);
        }
        else if (entertainment.getTag().toString().contains(catgy)){
            entertainment.setChecked(true);
        }






        cst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cst.getText().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = it.getText().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cs.getText().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = cv.getText().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dept = me.getText().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+dept, Toast.LENGTH_SHORT).show();
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = sports.getTag().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        cdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = cdc.getTag().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = club.getTag().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catgy = entertainment.getTag().toString();
                Toast.makeText(EditAndUpdateEventActivity.this, "hi"+catgy, Toast.LENGTH_SHORT).show();
            }
        });


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                eventUpdatedPic.setImageURI(result);

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

                            }
                        });

                    }
                });

            }
        });

        FloatingActionButton fab = findViewById(R.id.setPhoto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");

            }
        });

        // update the event...
        eventUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PBHorizontal.setVisibility(View.VISIBLE);

                CategoriesEvent categoriesEvent = new CategoriesEvent(imageEVT,
                        eventUpadtedName.getText().toString(),eventUpadtedDes.getText().toString(),dept,eventUpadtedOname.getText().toString(),eventUpadtedOnum.getText().toString(),eventUpadtedAdd.getText().toString(),
                        eventUpadtedDate.getText().toString(),eventUpadtedTime.getText().toString(),currentDateandTime, auth.getUid(), eventUpadtedLD.getText().toString(), eventUpadtedWCP.getText().toString(), islive,catgy);

                database.getReference().child("CollegeEvent").child("Categories").child(catgy).child(currentDateandTime).setValue(categoriesEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Snackbar.make(v, "Event Updated successfully", Snackbar.LENGTH_SHORT).show();
                            PBHorizontal.setVisibility(View.GONE);
                            finish();
                        }
                    }
                });
            }
        });













    }
}