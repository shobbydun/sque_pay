package com.example.squepay;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText editname, editemail, editpass1, editpass2;
    public ImageButton login, signup;
    private ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editname = findViewById(R.id.edit_name);
        editemail = findViewById(R.id.email_person1);
        editpass1 = findViewById(R.id.edit_pass1b);
        editpass2 = findViewById(R.id.edit_pass2);
        signup =  findViewById(R.id.imgsignup1);
        login = findViewById(R.id.imgbtn_login1);
        progressBar = findViewById(R.id.progressBar_regi);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginPage.class));
            }
        });
    }
    private void createUser(){
        String email = editemail.getText().toString();
        String password = editpass1.getText().toString();

        if (TextUtils.isEmpty(email)){
            editemail.setError("Email is empty abeg😏");
            editemail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            editpass1.setError("fill in the passsword🤷‍♂");
            editpass1.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"User register successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainPage.class));
                    }else {
                        Toast.makeText(MainActivity.this,"Its an error ;user registration error😑" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}




