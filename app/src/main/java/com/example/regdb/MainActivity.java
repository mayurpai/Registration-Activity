package com.example.regdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName,middleName,lastName,gender,dob,mobileNumber,eMail,nativePlace,password,cpassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.editTextTextPersonName);
        middleName = findViewById(R.id.editTextTextPersonName2);
        lastName = findViewById(R.id.editTextTextPersonName3);
        gender = findViewById(R.id.editTextTextPersonName4);
        dob = findViewById(R.id.editTextTextPersonName5);
        mobileNumber = findViewById(R.id.editTextPhone);
        eMail = findViewById(R.id.editTextTextEmailAddress);
        nativePlace = findViewById(R.id.editTextTextMultiLine);
        password = findViewById(R.id.editTextTextPassword);
        cpassword = findViewById(R.id.editTextTextPassword2);
        signin = findViewById(R.id.signIn);
        signup = findViewById(R.id.signUp);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = firstName.getText().toString();
                String mName = middleName.getText().toString();
                String lName = lastName.getText().toString();
                String sex = gender.getText().toString();
                String dateOfBirth = dob.getText().toString();
                String mob = mobileNumber.getText().toString();
                String email = eMail.getText().toString();
                String nativeplace = nativePlace.getText().toString();
                String pass = password.getText().toString();
                String cpass = cpassword.getText().toString();
                if(fName.equals("") || mName.equals("") || lName.equals("") || sex.equals("") || dateOfBirth.equals("") || mob.equals("") || email.equals("") || nativeplace.equals("") || pass.equals("") || cpass.equals("")) {
                    Toast.makeText(MainActivity.this, "Kindly Enter All The Details Carefully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(cpass)) {
                        Boolean checkuser = DB.checkUsername(email);
                        if(!checkuser) {
                            Boolean insert = DB.insertData(fName,mName,lName,sex,dateOfBirth,mob,email,nativeplace,pass,cpass);
                            if(insert) {
                                Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User Already Exists, Kindly Sign In!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Please Check The Password Typed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            }
        });
    }
}