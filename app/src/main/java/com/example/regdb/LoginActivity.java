package com.example.regdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email,password,name;
    Button signin1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextTextEmailAddress1);
        password = findViewById(R.id.editTextTextPassword1);
        signin1 = findViewById(R.id.signIn1);
        DB = new DBHelper(this);

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Kindly Enter All The Details Carefully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if(checkuserpass) {
                        Toast.makeText(LoginActivity.this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}