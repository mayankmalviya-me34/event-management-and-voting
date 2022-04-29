package com.example.minorproject.ParticipatedStudent.Aapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.Models.StudentModel;
import com.example.minorproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.viewHolder1>{
    Context context;
    ArrayList<StudentModel> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public StudentListAdapter(Context context, ArrayList<StudentModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public viewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stud_participation_list_rv,parent,false);
        return new viewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder1 holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        database.getReference("StudentProfilePicture").child(arrayList.get(position).getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).placeholder(R.drawable.facdp).into(holder.profilePhoto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.currentTD.setText(arrayList.get(position).getParticipateTime());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder1 extends RecyclerView.ViewHolder{
        TextView name,currentTD;
        ImageView profilePhoto;

        public viewHolder1(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.StudListName);
            profilePhoto = itemView.findViewById(R.id.StudListPhoto);
            currentTD = itemView.findViewById(R.id.CurrentTD);
        }
    }
}
