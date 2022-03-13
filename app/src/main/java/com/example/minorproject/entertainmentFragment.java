package com.example.minorproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class entertainmentFragment extends Fragment {
    Button create;



    public entertainmentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_entertainment, container, false);
       //create = view.findViewById(R.id.CreateEvtButton);
       return  view;
    }
}
