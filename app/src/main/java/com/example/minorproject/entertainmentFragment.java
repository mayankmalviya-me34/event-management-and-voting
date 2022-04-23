package com.example.minorproject;

import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minorproject.Models.FacultyModel;
import com.example.minorproject.Models.StudentInfoForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class entertainmentFragment extends Fragment {
    Button editbtn, saveInfobtn;
    TextView pdname, pdenrollment, pddept, pdbranch, pdaddress, pdgender, pdmobile, pdemail, pdacedemicYear,pdLinkedIn,stdname;
    EditText nametxt, enrollmenttxt, depttxt, branchtxt, addresstxt, gendertxt, mobiletxt, emailtxt, acedemicYeartxt,linkedIntxt;
    Dialog dialog;
    ImageButton setImage;
    ImageView profileImage;

    ActivityResultLauncher<String> launcher;
    FirebaseStorage storage;
    FirebaseDatabase database;
    FirebaseAuth auth;


    public entertainmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entertainment, container, false);
        //create = view.findViewById(R.id.CreateEvtButton);


        setImage = view.findViewById(R.id.changeProfile_image);
        //
        auth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        pdLinkedIn = view.findViewById(R.id.std_linkedIn_address);
        profileImage = view.findViewById(R.id.profile_image);
        pdname = view.findViewById(R.id.PDNameStd);
        pdacedemicYear = view.findViewById(R.id.PDyearStd);
        pdaddress = view.findViewById(R.id.PDaddressStd);
        pdbranch = view.findViewById(R.id.PDbranchStd);
        pddept = view.findViewById(R.id.PDdeptStd);
        pdemail = view.findViewById(R.id.PDEmailStd);
        pdenrollment = view.findViewById(R.id.PDEnrollmentStd);
        pdgender = view.findViewById(R.id.PDgenderStd);
        pdmobile = view.findViewById(R.id.PDNumberstd);
        editbtn = view.findViewById(R.id.button);


        database.getReference("StudentProfilePicture").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).placeholder(R.drawable.facdp).into(profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.getReference().child("StudentData").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("userID").getValue(String.class);
                pdname.setText(name);

                //Student info fetching..
                database.getReference("StudentInfoDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {

                            pdname.setText(snap.child("name").getValue(String.class).toUpperCase());
                            pddept.setText("Student of Department "+snap.child("dept").getValue(String.class).toUpperCase());
                            pdmobile.setText("Student Contact no. "+snap.child("mobile").getValue(String.class).toUpperCase());
                            pdbranch.setText("Student of Branch "+snap.child("branch").getValue(String.class).toUpperCase());
                            pdacedemicYear.setText("Student of "+snap.child("acedemicYear").getValue(String.class).toUpperCase());
                            pdaddress.setText("Student Address "+snap.child("address").getValue(String.class).toUpperCase());
                            pdenrollment.setText("Student Enrollment no. "+snap.child("enrollment").getValue(String.class).toUpperCase());
                            pdgender.setText("Student Gender "+snap.child("gender").getValue(String.class).toUpperCase());
                            pdLinkedIn.setText("Student LinkedIn Address "+snap.child("gender").getValue(String.class).toUpperCase());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pdemail.setText(auth.getCurrentUser().getEmail());


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                profileImage.setImageURI(result);
                Toast.makeText(getContext(), "successfully uploaded", Toast.LENGTH_SHORT).show();
                StorageReference reference = storage.getReference().child("stdProfileImage").child(auth.getUid());
                reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //          database.getReference().child("StudentProfilePictureNode").setValue(" nhi");
                                database.getReference().child("StudentProfilePicture").child(auth.getUid()).setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "successfully uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                    }
                });

            }
        });


        setImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });




        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, " hi", Snackbar.LENGTH_LONG).show();
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.student_info_form);
                Window window = dialog.getWindow();
                window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

                dialog.setCancelable(true);

                dialog.show();
                saveInfobtn = dialog.findViewById(R.id.saveStdInfo);
                nametxt = (EditText) dialog.findViewById(R.id.nametxt);
                branchtxt = (EditText) dialog.findViewById(R.id.branch);
                depttxt = (EditText) dialog.findViewById(R.id.departmenttxt);
                emailtxt = (EditText) dialog.findViewById(R.id.emailtxt);
                mobiletxt = (EditText) dialog.findViewById(R.id.mobileNumtxt);
                enrollmenttxt = (EditText) dialog.findViewById(R.id.enrollmenttxt);
                acedemicYeartxt = (EditText) dialog.findViewById(R.id.year);
                addresstxt = (EditText) dialog.findViewById(R.id.addresstxt);
                gendertxt = (EditText) dialog.findViewById(R.id.gendertxt);
                linkedIntxt = (EditText) dialog.findViewById(R.id.LinkedIntxt);

                emailtxt.setText(auth.getCurrentUser().getEmail());

               database.getReference("StudentInfoDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snap1) {
                        for (DataSnapshot snap:snap1.getChildren()) {
                            nametxt.setText(snap.child("name").getValue(String.class));
                            depttxt.setText(snap.child("dept").getValue(String.class));
                            mobiletxt.setText(snap.child("mobile").getValue(String.class));
                            branchtxt.setText(snap.child("branch").getValue(String.class));
                            acedemicYeartxt.setText(snap.child("acedemicYear").getValue(String.class));
                            addresstxt.setText(snap.child("address").getValue(String.class));
                            enrollmenttxt.setText(snap.child("enrollment").getValue(String.class));
                            gendertxt.setText(snap.child("gender").getValue(String.class));
                            //linkedIntxt.setText(snap.child("gender").getValue(String.class));
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                saveInfobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        database.getReference().child("StudentInfoDetails").child(auth.getUid()).setValue(new StudentInfoForm(nametxt.getText().toString(),
                                enrollmenttxt.getText().toString(), depttxt.getText().toString(), branchtxt.getText().toString(), addresstxt.getText().toString(),
                                gendertxt.getText().toString(), mobiletxt.getText().toString(), emailtxt.getText().toString(), acedemicYeartxt.getText().toString(),linkedIntxt.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Snackbar.make(v, "saving Information...", Snackbar.LENGTH_LONG).show();
                                }
                                final Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                }, 100);
                            }


                        });
                    }
                });

            }
        });
        return view;
    }
    }
