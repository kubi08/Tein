package com.kubisoft.tein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    Button startSignUp,startSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startSignUp=findViewById(R.id.startSignUpButton);
        startSignIn=findViewById(R.id.startSignInButton);

        startSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSignUpIntent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startSignUpIntent);
            }
        });
        startSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSignInIntent= new Intent(getApplicationContext(),Login.class);
                startActivity(startSignInIntent);
            }
        });
    }


}