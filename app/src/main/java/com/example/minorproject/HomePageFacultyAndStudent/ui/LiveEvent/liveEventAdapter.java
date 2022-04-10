package com.example.minorproject.HomePageFacultyAndStudent.ui.LiveEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.AfterSelectedCategories.CategoriesEvent;
import com.example.minorproject.R;
import com.squareup.picasso.Picasso;


import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;

public class liveEventAdapter extends RecyclerView.Adapter<liveEventAdapter.viewHolderLE> {
    ArrayList<CategoriesEvent> arrayList;
    Context context;
    boolean check = true;

    public liveEventAdapter(ArrayList<CategoriesEvent> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderLE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderLE(LayoutInflater.from(context).inflate(R.layout.live_event_recycle_view_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderLE holder, int position) {
        holder.LEname.setText(arrayList.get(position).getEvtTitle());
        holder.LEDept.setText(arrayList.get(position).getEvtDepartment());
        holder.LEOrgname.setText(arrayList.get(position).getEvtOrganizerName());
        holder.LEOrgnum.setText(arrayList.get(position).getEvtOrganizerNumber());
        holder.LEadd.setText(arrayList.get(position).getEvtAddress());
        holder.LEtime.setText(arrayList.get(position).getEvtTime());
        Picasso.get().load(arrayList.get(position).getEvtPhoto()).into(holder.LEpic);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolderLE extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView LEname,LEDept,LEOrgname,LEOrgnum,LEparticipants,LEadd,LEtime;
        ImageView FacPic,LEpic;
        TextView one,two,three,four;
        LinearLayout linearLayout;


        public viewHolderLE(@NonNull View itemView) {
            super(itemView);

            LEname = itemView.findViewById(R.id.liveEventName);
            LEDept = itemView.findViewById(R.id.liveEventDept);
            //
            LEOrgname = itemView.findViewById(R.id.liveEventOName);
            LEOrgnum = itemView.findViewById(R.id.liveEventONum);
            LEparticipants = itemView.findViewById(R.id.liveEvent_participants);
            LEadd = itemView.findViewById(R.id.liveEventAdd);
            FacPic = itemView.findViewById(R.id.liveEventFac_profile_image);
            LEpic = itemView.findViewById(R.id.liveEventPic);
            LEtime = itemView.findViewById(R.id.liveEventTime);
            one = itemView.findViewById(R.id.oneANI);
            two = itemView.findViewById(R.id.twoAni);
            three = itemView.findViewById(R.id.threeAni);
            four = itemView.findViewById(R.id.fourAni);
            linearLayout = itemView.findViewById(R.id.LElinear);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            if(check==true){
                LEadd.setVisibility(View.VISIBLE);
                LEOrgnum.setVisibility(View.VISIBLE);
                LEOrgname.setVisibility(View.VISIBLE);
                LEparticipants.setVisibility(View.VISIBLE);
                FacPic.setVisibility(View.VISIBLE);
                LEtime.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                check = false;
            }
            else{
                LEadd.setVisibility(View.GONE);
                LEOrgnum.setVisibility(View.GONE);
                LEOrgname.setVisibility(View.GONE);
                LEparticipants.setVisibility(View.GONE);
                FacPic.setVisibility(View.GONE);
                LEtime.setVisibility(View.GONE);
                one.setVisibility(View.GONE);
                two.setVisibility(View.GONE);
                three.setVisibility(View.GONE);
                four.setVisibility(View.GONE);
                check = true;
            }

        }
    }
}
