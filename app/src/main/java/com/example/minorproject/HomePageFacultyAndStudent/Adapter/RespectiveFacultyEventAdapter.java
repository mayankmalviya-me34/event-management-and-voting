package com.example.minorproject.HomePageFacultyAndStudent.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.FacultyEventRelated.EditAndUpdateEventActivity;
import com.example.minorproject.ParticipatedStudent.ParticipatedStudentDetailsAndList;
import com.example.minorproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class RespectiveFacultyEventAdapter extends RecyclerView.Adapter<RespectiveFacultyEventAdapter.FacViewHolder>{
    ArrayList<CategoriesEvent> arrayList;
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    AnimationDrawable animationDrawable;


    public RespectiveFacultyEventAdapter(ArrayList<CategoriesEvent> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public FacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FacViewHolder(LayoutInflater.from(context).inflate(R.layout.edit_and_details_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FacViewHolder holder, int position) {

        String evtN,evtPic,evtDes,evtOname,evtOnum,evtTime,evtDate,evtDept,evtAdd,evtLD,evtWCP,evtCatg,evtDT_ID,evt_isLive,facID;
        evtN = arrayList.get(position).getEvtTitle();
        evtPic = arrayList.get(position).getEvtPhoto();
        evtDes = arrayList.get(position).getEvtDescription();
        evtOname = arrayList.get(position).getEvtOrganizerName();
        evtOnum = arrayList.get(position).getEvtOrganizerNumber();
        evtDate = arrayList.get(position).getEvtDate();
        evtTime = arrayList.get(position).getEvtTime();
        evtDept = arrayList.get(position).getEvtDepartment();
        evtAdd = arrayList.get(position).getEvtAddress();
        evtLD = arrayList.get(position).getLastDate();
        evtWCP = arrayList.get(position).getEligibility();
        evtCatg = arrayList.get(position).getEvtCatg();
        evtDT_ID = arrayList.get(position).getCurrentDateTime();
        evt_isLive = arrayList.get(position).getIsLive();
        facID = arrayList.get(position).getFacultyID();

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, ParticipatedStudentDetailsAndList.class);
                intent.putExtra("evtID",evtDT_ID);
                context.startActivity(intent);
            }
        });

        database.getReference("ParticipatedStudentList").child(evtDT_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = 0;
                for (DataSnapshot snap: snapshot.getChildren()) {

                     count ++;
                }
                holder.noOfP.setText(""+count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(evt_isLive.contains("1")){
            try{
                animationDrawable = (AnimationDrawable) holder.live.getBackground();
                animationDrawable.setExitFadeDuration(500);
                animationDrawable.setEnterFadeDuration(1000);
                animationDrawable.start();
            }
            catch (Exception e){

            }

            holder.live.setText("LIVE");

        }else {
            holder.live.setText("make it live");
            holder.live.setBackgroundColor(Color.GREEN);

        }

        holder.evtName.setText(evtN);
        Picasso.get().load(evtPic).into(holder.evtPhoto);
        holder.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "working..", Toast.LENGTH_SHORT).show();
            }
        });

        //
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button delete1,cancle;

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.my_custom_dialog_box);
                delete1 = dialog.findViewById(R.id.DBdelete);
                cancle = dialog.findViewById(R.id.DBcancel);
                dialog.show();


               delete1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                       CategoriesEvent categoriesEvent = new CategoriesEvent(evtPic,evtN,evtDes,evtDept,evtOname,evtOnum,evtAdd,evtDate,evtTime,evtDT_ID,facID,evtLD,evtWCP,evt_isLive,evtCatg);
                       database.getReference().child("allEvent").child(facID).child(evtDT_ID).setValue(categoriesEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){

                                   Snackbar.make(v,"all event",Snackbar.LENGTH_LONG).show();
                               }else{
                                   Snackbar.make(v,"Please check your Network connection",Snackbar.LENGTH_SHORT).show();
                               }
                           }
                       });
                       Query query = database.getReference("CollegeEvent").child("Categories").child(evtCatg).orderByChild("currentDateTime").equalTo(evtDT_ID);
                       query.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               for (DataSnapshot snap:snapshot.getChildren()) {
                                   snap.getRef().removeValue();
                               }

                               Snackbar.make(v,"Deleted",Snackbar.LENGTH_SHORT).show();
                               dialog.dismiss();


                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

                       arrayList.clear();
                   }
               });

               cancle.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });



            }
        });
        holder.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(evt_isLive.contains("1")){
                   Button delete1,cancle;

                   Dialog dialog = new Dialog(context);
                   dialog.setContentView(R.layout.my_custom_dialog_box);
                   delete1 = dialog.findViewById(R.id.DBdelete);
                   cancle = dialog.findViewById(R.id.DBcancel);
                   delete1.setText("Inactive");
                   dialog.show();
                   delete1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();


                           holder.live.setBackgroundColor(Color.GREEN);
                           holder.live.setText("Make it live");
                           database.getReference("CollegeEvent").child("Categories").child(evtCatg).child(evtDT_ID).child("isLive").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){

                                       Snackbar.make(v,"Now your event is inactive",Snackbar.LENGTH_LONG).show();
                                   }else{
                                       Snackbar.make(v,"Please check your Network connection",Snackbar.LENGTH_SHORT).show();
                                   }

                               }
                           });
                       }
                   });
                   cancle.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                       }
                   });
                   arrayList.clear();
               }
               else {
                   holder.live.setText("live");


                   database.getReference("CollegeEvent").child("Categories").child(evtCatg).child(evtDT_ID).child("isLive").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){

                               Snackbar.make(v,"Now your event is live",Snackbar.LENGTH_LONG).show();
                           }else{
                               Snackbar.make(v,"Please check your Network connection",Snackbar.LENGTH_SHORT).show();
                           }
                       }
                   });
                  arrayList.clear();

                   holder.live.setText("LIVE");
               }


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FacViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView evtPhoto;
        TextView evtName, noOfP;
        Button delete, live;
        Button view;
        public FacViewHolder(@NonNull View itemView) {
            super(itemView);
            evtPhoto = itemView.findViewById(R.id.imageViewEventTea);
            evtName = itemView.findViewById(R.id.eventTitleTea);
            delete = itemView.findViewById(R.id.editBtn);
            live = itemView.findViewById(R.id.LiveBtn);
            noOfP = itemView.findViewById(R.id.eventNumParticipants);
            view = itemView.findViewById(R.id.viewParticipatedStud);
            itemView.setOnClickListener(this::onClick);
        }


        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            Intent intent = new Intent(context, EditAndUpdateEventActivity.class);
            intent.putExtra("name",arrayList.get(pos).getEvtTitle());
            intent.putExtra("des",arrayList.get(pos).getEvtDescription());
            intent.putExtra("dept",arrayList.get(pos).getEvtDepartment());
            intent.putExtra("Oname",arrayList.get(pos).getEvtOrganizerName());
            intent.putExtra("Onum",arrayList.get(pos).getEvtOrganizerNumber());
            intent.putExtra("date",arrayList.get(pos).getEvtDate());
            intent.putExtra("time",arrayList.get(pos).getEvtTime());
            intent.putExtra("add",arrayList.get(pos).getEvtAddress());
            intent.putExtra("pic",arrayList.get(pos).getEvtPhoto());
            intent.putExtra("WCP",arrayList.get(pos).getEligibility());
            intent.putExtra("LD",arrayList.get(pos).getLastDate());
            intent.putExtra("catg",arrayList.get(pos).getEvtCatg());
            intent.putExtra("DT",arrayList.get(pos).getCurrentDateTime());
            intent.putExtra("Live",arrayList.get(pos).getIsLive());
            context.startActivity(intent);
        }
    }
}
