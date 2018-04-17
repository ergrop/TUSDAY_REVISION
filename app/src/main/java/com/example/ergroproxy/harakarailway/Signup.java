package com.example.ergroproxy.harakarailway;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private Button create;
    private EditText name;
    private EditText useremail;
    private EditText userpassword;



    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       getSupportActionBar().hide();

        setContentView(R.layout.activity_signup);

        progressDialog =new ProgressDialog(this);

        create= (Button)findViewById(R.id.create_button);
        name = (EditText) findViewById(R.id.user_name);
        userpassword= (EditText) findViewById(R.id.user_password);
        useremail= (EditText) findViewById(R.id.user_email);


        create.setOnClickListener(this);



        firebaseAuth= FirebaseAuth.getInstance();
    }


    private void registerUser(){
        String uname = name.getText().toString().trim();
        String Uemail =useremail.getText().toString().trim();
        String Upassword =userpassword.getText().toString().trim();

        if (TextUtils.isEmpty(uname)){
            //name is empty
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Uemail)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Upassword)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Please wait....");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(Uemail,Upassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Signup.this, "Registration success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else{
                            Toast.makeText(Signup.this, "Could not register, please ty again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }



    @Override
    public void onClick(View view) {
        if (view ==create){
            registerUser();

        }
            }

    public void clickHandler(View view) {
        if (view.getId() == R.id.text_View){
            startActivity(new Intent(getApplicationContext(), Signin.class));
        }
    }

}



