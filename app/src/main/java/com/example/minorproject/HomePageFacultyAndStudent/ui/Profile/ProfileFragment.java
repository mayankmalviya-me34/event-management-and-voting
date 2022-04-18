package com.example.minorproject.HomePageFacultyAndStudent.ui.Profile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.minorproject.Models.FacultyModel;
import com.example.minorproject.R;
import com.example.minorproject.databinding.FragmentProfileBinding;
import com.example.minorproject.loginandregister.FacultyLoginActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    FirebaseAuth auth;
    TextView facName,facEmail;
    ImageButton setImage;
    ActivityResultLauncher<String> launcher;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Animation animation;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        //animation
        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.bottom_anim);

       // binding.GridLayout.setAnimation(animation);

        //hooks
        facEmail = root.findViewById(R.id.fac_profile_email);
        facName = root.findViewById(R.id.fac_profile_name);
        setImage = root.findViewById(R.id.fac_profile_set_pic);
        database.getReference().child("FacultyProfilePicture").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(binding.profileImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.getReference().child("FacultyData").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("userID").getValue(String.class);
                facName.setText(name);
                //Faculty info fetching..
                database.getReference("FacultyPersonalDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap: snapshot.getChildren()) {
                            binding.PDEmailFac.setText(snap.child("newEmail").getValue(String.class));
                            binding.PDNameFac.setText(snap.child("newName").getValue(String.class));
                            binding.PDDeptFac.setText(snap.child("newDept").getValue(String.class));
                            binding.PDNumberFac.setText(snap.child("newPhno").getValue(String.class));
                            binding.PDFacultyOfFac.setText(snap.child("facultyOf").getValue(String.class));
                           // Toast.makeText(getActivity(), ""+snap.child("newEmail").getValue(String.class), Toast.LENGTH_SHORT).show();

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

        facEmail.setText(auth.getCurrentUser().getEmail());



        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.profileImage.setImageURI(result);



                StorageReference reference  = storage.getReference().child("FacProfileImage").child(auth.getUid());
                reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("FacultyProfilePicture").child(auth.getUid()).setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getActivity(), "Upload successfully", Toast.LENGTH_SHORT).show();
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

        // updating personal details form here...
        binding.PDUpdateFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getReference().child("FacultyPersonalDetails").child(auth.getUid()).setValue(new FacultyModel(binding.PDEmailFac.getText().toString(),auth.getUid(),binding.PDNameFac.getText().toString(),binding.PDDeptFac.getText().toString(),binding.PDNumberFac.getText().toString(),binding.PDFacultyOfFac.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar.make(v,"Respected Faculty your details are up-to-date",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        ImageButton logoutProfile;
        logoutProfile = root.findViewById(R.id.Gridlayout_login);
       logoutProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Button yes,no;

               Dialog dialog = new Dialog(getActivity());
               dialog.setContentView(R.layout.my_custom_dialog_box);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

               no = dialog.findViewById(R.id.DBcancel);
               yes = dialog.findViewById(R.id.DBdelete);

               yes.setText("Logout");
               dialog.show();

               yes.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       auth.signOut();
                       startActivity(new Intent(getActivity(),FacultyLoginActivity.class));

                   }
               });
               no.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
           }
       });











//
//        binding.fctlogoutbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                auth.signOut();
//                startActivity(new Intent(getContext(), FacultyLoginActivity.class));
//            }
//        });
        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}