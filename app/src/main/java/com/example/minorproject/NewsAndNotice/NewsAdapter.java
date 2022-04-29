package com.example.minorproject.NewsAndNotice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minorproject.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder5> {
    ArrayList<String> arrayList;
    Context context;


    public NewsAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder5(LayoutInflater.from(context).inflate(R.layout.news_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder5 holder, int position) {



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder5 extends RecyclerView.ViewHolder {

        public viewHolder5(@NonNull View itemView) {
            super(itemView);

        }
    }
}
