package com.example.minorproject.HomePageFacultyAndStudent.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;



import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.HomePageFacultyAndStudent.Adapter.RespectiveFacultyEventAdapter;
import com.example.minorproject.R;

import com.example.minorproject.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    ArrayList<CategoriesEvent> arrayList = new ArrayList<>();
    String live="";
    TextView menuName, menuEmail,setting, contactUs, logout,AllEvent;
    ImageView cancleMenu,facPic;

    String[] catgArr = {"sports","entertainment","club","cdc"};

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.eventEditAndDetailsCheckRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RespectiveFacultyEventAdapter adapter = new RespectiveFacultyEventAdapter(arrayList,getContext());
        for(int a=0; a< catgArr.length; a++){
            arrayList.clear();
            database.getReference("CollegeEvent").child("Categories").child(catgArr[a]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot s : snapshot.getChildren()) {
                        if(auth.getUid().contains(s.child("facultyID").getValue(String.class))){
                            //Toast.makeText(getContext(),"working",Toast.LENGTH_LONG).show();

                            arrayList.add(new CategoriesEvent(s.child("evtPhoto").getValue(String.class),s.child("evtTitle").getValue(String.class),s.child("evtDescription").getValue(String.class), s.child("evtDepartment").getValue(String.class),s.child("evtOrganizerName").getValue(String.class),s.child("evtOrganizerNumber").getValue(String.class),s.child("evtAddress").getValue(String.class),s.child("evtDate").getValue(String.class),s.child("evtTime").getValue(String.class),s.child("currentDateTime").getValue(String.class),s.child("facultyID").getValue(String.class),s.child("lastDate").getValue(String.class), s.child("eligibility").getValue(String.class),s.child("isLive").getValue(String.class),s.child("evtCatg").getValue(String.class)));
                            adapter.notifyDataSetChanged();

                        }


                    }


//                binding.progressbar.setVisibility(View.GONE);
//                binding.CategoryRecycle.setVisibility(View.VISIBLE);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
            adapter.notifyDataSetChanged();
            binding.eventEditAndDetailsCheckRV.setAdapter(adapter);
        }
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.menu_faculty_layout);

        binding.menuFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //hooks
        menuName = dialog.findViewById(R.id.CurrentFacMenuName);
        menuEmail = dialog.findViewById(R.id.CurrentFacMenuEmail);
        setting = dialog.findViewById(R.id.menu_setting);
        contactUs = dialog.findViewById(R.id.ContactUsButton);
        logout = dialog.findViewById(R.id.LogoutButton);
        AllEvent = dialog.findViewById(R.id.all_event);
        cancleMenu = dialog.findViewById(R.id.cancelMenu_Fac);
        facPic = dialog.findViewById(R.id.CurrentFacMenuPic);
        database.getReference("FacultyData").child(auth.getUid()).child("userID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                menuName.setText(name);
                //Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        menuEmail.setText(auth.getCurrentUser().getEmail());


        cancleMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}