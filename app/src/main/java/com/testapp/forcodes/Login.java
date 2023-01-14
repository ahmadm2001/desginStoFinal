package com.testapp.forcodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        final EditText phone = findViewById(R.id.phone);
        final EditText email = findViewById(R.id.email1);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Login.this, "please enter your login data", Toast.LENGTH_SHORT).show();
                }
                else {

                    startActivity(new Intent(Login.this,Home.class));
                }

            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open Register activity
                startActivity(new Intent(Login.this,Register.class));

            }
        });

    }
//    private boolean verifyPass()
//    {
//        String pass=editPass.getEditText().getText().toString().trim();
//        if(pass.isEmpty())
//        {   editPass.setErrorEnabled(true);
//            editPass.setError("Password Required");
//            return true;
//        }
//        else
//        {
//            editPass.setErrorEnabled(false);
//            return false;
//        }
//    }
}