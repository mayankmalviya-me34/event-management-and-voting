package com.example.minorproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minorproject.AfterSelectedCategories.AfterSelectedActitvity;
import com.example.minorproject.databinding.FragmentHomeBinding;
import com.example.minorproject.databinding.FragmentLangBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class langFragment extends Fragment {
    FragmentLangBinding binding;





    public langFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_lang, container, false);
        binding = FragmentLangBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //

        Intent intent = new Intent(getContext(), AfterSelectedActitvity.class);

        binding.CardViewSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("catg",binding.CardViewSports.getTag().toString());
                startActivity(intent);

            }
        });
        binding.CardViewEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("catg",binding.CardViewEntertainment.getTag().toString());
                startActivity(intent);

            }
        });
        binding.CardViewCDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("catg",binding.CardViewCDC.getTag().toString());
                startActivity(intent);

            }
        });

        binding.CardViewClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("catg",binding.CardViewClubs.getTag().toString());
                startActivity(intent);

            }
        });
        return root;
    }
}