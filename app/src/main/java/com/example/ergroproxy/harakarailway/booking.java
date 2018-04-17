package com.example.ergroproxy.harakarailway;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class booking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner sp;

    ArrayAdapter<CharSequence> adapter;

    EditText firstname;
    EditText lastname;
    EditText passid;
    TextView dateselec;
    RadioButton radiobuttton;

    Button reset;
    Button can;

    ImageButton mpesabutton;

    @SuppressLint("StaticFieldLeak")
    public static Button confimB;


    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_booking);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Passengers");



        sp = findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.Destination_and_Prices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "Item selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button datebutton = findViewById(R.id.Dbutton);

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dpicker = new Datepicker();
                dpicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        firstname = findViewById(R.id.Fname);
        lastname = findViewById(R.id.Lname);
        passid = findViewById(R.id.IDNO);
        radiobuttton = findViewById(R.id.radio1);
        dateselec = findViewById(R.id.textView2);

        confimB = findViewById(R.id.confirmationb);
        confimB.setVisibility(View.GONE);

        mpesabutton = findViewById(R.id.m_pesa);

        mpesabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mpesabutton){
                    startActivity(new Intent(getApplicationContext(), Payment.class));
                }


            }
        });

        reset = findViewById(R.id.button2);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == reset){
                    startActivity(new Intent(booking.this, booking.class));
                }
            }
        });

        can = findViewById(R.id.button3);

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == can){

                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
            }
        });



        confimB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == confimB){
                     firedata();

                }
            }
        });


    }


     public void firedata(){
       String Firstname = firstname.getText().toString().trim();
       String Lastname = lastname.getText().toString().trim();
       String PassangerID = passid.getText().toString().trim();
       String spin = sp.getSelectedItem().toString();
       String RadioB = radiobuttton.getText().toString().trim();
       String Dateselected = dateselec.getText().toString().trim();

       Data01 data01 = new Data01(Firstname, Lastname, PassangerID, RadioB, Dateselected, spin);

       databaseReference.setValue(data01);





        Toast.makeText(this, "booking is completed", Toast.LENGTH_LONG).show();

    }




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textView = findViewById(R.id.textView2);
        textView.setText(currentDateString);



    }

}
