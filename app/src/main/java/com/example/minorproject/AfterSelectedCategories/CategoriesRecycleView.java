package com.example.minorproject.AfterSelectedCategories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;

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

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView title,Dept,Time;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventTitle);
            Dept = itemView.findViewById(R.id.eventDepartment);
            Time = itemView.findViewById(R.id.eventTime);
        }
    }
}
