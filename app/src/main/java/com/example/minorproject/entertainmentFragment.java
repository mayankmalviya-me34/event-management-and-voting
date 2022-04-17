package com.example.minorproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class entertainmentFragment extends Fragment {
     Button editbtn,saveInfobtn;
    TextView textView;
    Dialog dialog;



    public entertainmentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_entertainment, container, false);
       //create = view.findViewById(R.id.CreateEvtButton);
        
          textView = view.findViewById(R.id.semi);



        editbtn = view.findViewById(R.id.button);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v," hi",Snackbar.LENGTH_LONG).show();
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.student_info_form);
                Window window = dialog.getWindow();
                window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

                dialog.setCancelable(true);

                dialog.show();
            }
        });



        textView.setText(" hiiiii");
       return  view;
    }
}
