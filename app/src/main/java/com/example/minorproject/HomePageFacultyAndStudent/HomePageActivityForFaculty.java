package com.example.minorproject.HomePageFacultyAndStudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minorproject.HomePageFacultyAndStudent.CreateEvent.CreateEventScrollingActivity;
import com.example.minorproject.R;
import com.example.minorproject.databinding.ActivityHomePageForFacultyBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class HomePageActivityForFaculty extends AppCompatActivity {

    private ActivityHomePageForFacultyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageForFacultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingActionButtonCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePageActivityForFaculty.this, CreateEventScrollingActivity.class));
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_LE, R.id.navigation_profile)
                .build();
      NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home_page_for_faculty);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       NavigationUI.setupWithNavController(binding.navView, navController);
    }

}