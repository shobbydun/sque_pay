package com.example.squepay;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class removeitem extends AppCompatActivity {
    public static TextView resultdeleteview;
    private FirebaseAuth firebaseAuth;
    Button scantodelete, deletebtn;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removeitem);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        resultdeleteview = findViewById(R.id.barcodedelete);
        scantodelete = findViewById(R.id.buttonscandelete);
        deletebtn= findViewById(R.id.deleteItemToTheDatabasebtn);

        scantodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivitydel.class));
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefrmdatabase();
            }
        });

    }

    public void deletefrmdatabase()
    {
        String deletebarcodevalue = resultdeleteview.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String resultemail = finaluser.replace(".","");
        if(!TextUtils.isEmpty(deletebarcodevalue)){
            databaseReference.child(resultemail).child("Items").child(deletebarcodevalue).removeValue();
            Toast.makeText(removeitem.this,"Item is Deleted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(removeitem.this,"Please scan Barcode",Toast.LENGTH_SHORT).show();
        }
    }
}
