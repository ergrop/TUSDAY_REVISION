package com.example.ergroproxy.harakarailway;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class Payment extends AppCompatActivity {

    EditText p_in;
    Button okay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_payment);

        p_in = findViewById(R.id.pin_area);
        okay = findViewById(R.id.ok_button);

        okay.setEnabled(false);

        p_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    okay.setEnabled(false);
                }
                else
                    okay.setEnabled(true);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking.confimB.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                PendingIntent pintent = PendingIntent.getActivity(Payment.this,0,intent,0);
                Notification noti = new Notification.Builder(Payment.this)
                        .setTicker("TickerTitle")
                        .setContentTitle("Harraka Booking")
                        .setContentText("Payment of the ticket is confirmed")
                        .setSmallIcon(R.drawable.mpay)
                        .setContentIntent(pintent).getNotification();

                noti.flags = Notification.FLAG_AUTO_CANCEL;
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0,noti);
            }
        });


    }


}
