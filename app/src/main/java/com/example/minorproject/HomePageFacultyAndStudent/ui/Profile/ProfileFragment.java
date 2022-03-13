package com.example.minorproject.HomePageFacultyAndStudent.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import com.example.minorproject.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //
        auth = FirebaseAuth.getInstance();

        binding.fctlogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
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