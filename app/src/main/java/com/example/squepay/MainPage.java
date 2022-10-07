package com.example.squepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    TextView firebasenameview;
    Button toast;

    private ImageButton additems, removeitems, scanitems;
    private Button viewcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        firebasenameview = findViewById(R.id.firebasename);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser = users.getEmail();
        String result = finaluser.substring(0, finaluser.indexOf("@"));
        String resultemail = result.replace(".", "");
        firebasenameview.setText("Welcome, " + resultemail);
        viewcart = findViewById(R.id.btn_cart);
        removeitems = findViewById(R.id.btn_remove);
        scanitems = findViewById(R.id.btn_add);
        additems = findViewById(R.id.btn_items);

        additems.setOnClickListener(this);
        removeitems.setOnClickListener(this);
        viewcart.setOnClickListener(this);
        scanitems.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      viewcart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(MainPage.this,AddCart.class));
          }
      });
        removeitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this,removeitem.class));
            }
        });
        scanitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this,ScanItem.class));
            }
        });
        additems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this,me_Item.class));
            }
        });
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainPage.this, LoginPage.class));
        Toast.makeText(MainPage.this, "Logout Successful see you again😥", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu: {
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}