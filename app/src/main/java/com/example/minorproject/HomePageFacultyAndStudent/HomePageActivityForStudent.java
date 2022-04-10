package com.example.minorproject.HomePageFacultyAndStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.minorproject.HomeFragmentStudent;
import com.example.minorproject.R;
import com.example.minorproject.entertainmentFragment;
import com.example.minorproject.langFragment;
import com.example.minorproject.loginandregister.StudentLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class HomePageActivityForStudent extends AppCompatActivity {
    NavigationView nav;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        auth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistner);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbartemp);
        setSupportActionBar(toolbar);
        nav= findViewById(R.id.navmenu);
        drawerLayout  = (DrawerLayout)findViewById(R.id.drawer);

        toggle  = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_menu,R.string.close_menu);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Home:
                        Toast.makeText(getApplicationContext(), "clicked On home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.setting:
                        Toast.makeText(getApplicationContext(), "clicked On setting", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.about:
                        Toast.makeText(getApplicationContext(), "clicked On about", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        Toast.makeText(getApplicationContext(), "clicked On logout", Toast.LENGTH_SHORT).show();
                        auth.signOut();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomePageActivityForStudent.this, StudentLoginActivity.class));
                        break;
                }

                return true;
            }
        });
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;
                    switch ((item.getItemId()))
                    {
                        case R.id.Home:
                            selectFragment = new HomeFragmentStudent();
                            break;
                        case R.id.language:
                            selectFragment = new langFragment();
                            break;
                        case R.id.seminars:
                            selectFragment = new entertainmentFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();
                    return true;
                }
            };

    }
