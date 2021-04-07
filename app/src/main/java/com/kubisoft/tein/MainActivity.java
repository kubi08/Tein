package com.kubisoft.tein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    EditText signUpEmail,signUpPassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUpEmail=findViewById(R.id.signUpEmail);
        signUpPassword=findViewById(R.id.signUpPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void gotoSignIn(View view)
    {
        startActivity(new Intent(MainActivity.this,Login.class));
    }

    public void signUp(View view)
    {

        String email=signUpEmail.getText().toString();
        String password=signUpPassword.getText().toString();

        if(email.isEmpty()==false && password.length()>=8)
        {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {

                                signUpEmail.setText("");
                                signUpPassword.setText("");
                                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                            } else
                            {
                                signUpEmail.setText("");
                                signUpPassword.setText("");
                                Toast.makeText(getApplicationContext(),task.toString(),Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });

        }
        else
        {

            Toast.makeText(getApplicationContext(),"Please input valid data",Toast.LENGTH_LONG).show();
        }
    }
}