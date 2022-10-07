package com.example.squepay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class LoginPage extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private ImageButton Login1;
    private ImageButton signup1;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Email = findViewById(R.id.email_person1);
        Password = findViewById(R.id.edit_pass1b);
        Login1 = findViewById(R.id.imgbtn_login1);
        signup1 = findViewById(R.id.imgsignup1);
        progressBar = (ProgressBar) findViewById(R.id.progressBarlogin);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, MainActivity.class));

            }
        });


        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());

            }
        });
    }

    public void validate(String useremail, String userpassword) {
        progressDialog.setMessage(".....Please wait....seconds only⏳....");
        progressDialog.show();

        auth.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginPage.this, "Login Success🎉🎊", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginPage.this, MainPage.class));
                } else {
                    Toast.makeText(LoginPage.this, "Login failed try again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}


