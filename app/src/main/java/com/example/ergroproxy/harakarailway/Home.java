package com.example.ergroproxy.harakarailway;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private ActionBarDrawerToggle mToggle;
    private NavigationView nav_view;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, Signin.class));
        }


        DrawerLayout draw = findViewById(R.id.hom_e);
        mToggle = new ActionBarDrawerToggle(this, draw, R.string.open, R.string.close);
        mToggle.setDrawerIndicatorEnabled(true);

        draw.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView nav_view = findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.hom)

                {
                    Toast.makeText(Home.this, "MyProfile", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.act) {
                    Toast.makeText(Home.this, "activity", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.sett) {
                    Toast.makeText(Home.this, "setting", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.log_out) {
                    FirebaseAuth.getInstance().signOut();

                    AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                    builder.setMessage("Do you want to Log Out");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            startActivity(new Intent(getApplicationContext(), Signin.class));

                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setMessage("Do you want to Exit");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }


    public void clickHandler(MenuItem item) {
        if (item.getItemId() == R.id.book) {
            startActivity(new Intent(getApplicationContext(), booking.class));
        }
    }

}

