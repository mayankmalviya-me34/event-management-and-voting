package com.example.minorproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minorproject.loginandregister.OnTapNotoficationActivity;
import com.google.android.material.snackbar.Snackbar;


public class CreateEventFragment extends Fragment {
Button creatEvnt;
Context context;
EditText getEVtTitle,getDescription,getDate,getLocation,getHostingDept, getHost;
    private static final String CHANNEL_ID = "com.example.minorproject";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_event, container, false);


        creatEvnt = view.findViewById(R.id.CreateEvtButton);
        getEVtTitle = view.findViewById(R.id.getEventTitle);
        getDescription = view.findViewById(R.id.getDescription);
        getDate = view.findViewById(R.id.getDate);
        getLocation = view.findViewById(R.id.getLocation);
        getHostingDept = view.findViewById(R.id.getHostingDepartment);
        getHost = view.findViewById(R.id.getHost);





        creatEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getEVtTitle.getText().toString();
                String descript = getEVtTitle.getText().toString();
                String date = getEVtTitle.getText().toString();
                String location = getEVtTitle.getText().toString();
                String hostingDept = getEVtTitle.getText().toString();
                String host = getEVtTitle.getText().toString();
                Toast.makeText(getContext(), "Event Created Successfully", Toast.LENGTH_SHORT).show();
                createNotificationChannel();
                Intent intent = new Intent(getContext(), OnTapNotoficationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
                String eventT = getEVtTitle.getText().toString();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.notifications_icon)
                        .setContentTitle(eventT)
                        .setContentText("Hello Acropolians... New Event is coming..participate now")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                int notificationId = 512333;
                notificationManager.notify(notificationId, builder.build());


            }
        });
        return view;
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}