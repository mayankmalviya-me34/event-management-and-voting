package com.example.minorproject.AfterSelectedCategories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesRecycleView extends RecyclerView.Adapter<CategoriesRecycleView.viewHolder> {
    ArrayList<CategoriesEvent> arrayList;
    Context context;

    public CategoriesRecycleView(ArrayList<CategoriesEvent> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_recycle_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getEvtTitle());
        holder.Dept.setText(arrayList.get(position).getEvtDepartment());
        holder.Time.setText("Time:- "+arrayList.get(position).getEvtTime());
        holder.date.setText("Date:- "+arrayList.get(position).getEvtDate());
        Picasso.get().load(arrayList.get(position).getEvtPhoto()).placeholder(R.drawable.logo).into(holder.image);

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,Dept,Time, date;
        ImageView image;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventTitle);
            Dept = itemView.findViewById(R.id.eventDepartment);
            Time = itemView.findViewById(R.id.eventTime);
            date = itemView.findViewById(R.id.eventDate);
            image = itemView.findViewById(R.id.imageViewEvent);
            itemView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View v) {
            int pos = this.getAdapterPosition();
            Intent intent = new Intent(context,SelectedEventActivity.class);
            intent.putExtra("photo",arrayList.get(pos).getEvtPhoto());
            intent.putExtra("name",arrayList.get(pos).getEvtTitle());
            intent.putExtra("description",arrayList.get(pos).getEvtDescription());
            intent.putExtra("dept",arrayList.get(pos).getEvtDepartment());
            intent.putExtra("address",arrayList.get(pos).getEvtAddress());
            intent.putExtra("date",arrayList.get(pos).getEvtDate());
            intent.putExtra("time",arrayList.get(pos).getEvtTime());
            intent.putExtra("org",arrayList.get(pos).getEvtOrganizerName());
            intent.putExtra("orgPh",arrayList.get(pos).getEvtOrganizerNumber());
            intent.putExtra("eventID",arrayList.get(pos).getCurrentDateTime());
            context.startActivity(intent);

        }
    }
}
