package com.kubisoft.tein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText signInEmail,signInPassword,profilePhone;
    ProgressBar bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        signInEmail=findViewById(R.id.signInEmail);
        signInPassword=findViewById(R.id.signInPassword);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(Login.this, Profile.class);
            startActivity(intent);
            finish();
        }
    }
    public void signInToSignUp(View view){
        Intent signInToSignUp = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(signInToSignUp);
    }

    public void signInHere(View view)
    {
        bar.setVisibility(View.VISIBLE);
        String email=signInEmail.getText().toString();
        String password=signInPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            bar.setVisibility(View.INVISIBLE);
                            Intent intent=new Intent(Login.this,Profile.class);
                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                            startActivity(intent);
                        } else
                        {
                            bar.setVisibility(View.INVISIBLE);
                            signInEmail.setText("");
                            signInPassword.setText("");
                            Toast.makeText(getApplicationContext(),task.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}