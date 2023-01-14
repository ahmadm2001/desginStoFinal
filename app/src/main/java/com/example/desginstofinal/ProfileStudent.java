package com.example.desginstofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileStudent extends AppCompatActivity {


    Intent intent;
    Student student=null;
    EditText Name,Phone,Id,txtEmail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);
         intent=getIntent();
         Name=findViewById(R.id.Name);
         Phone=findViewById(R.id.Phone);
         txtEmail=findViewById(R.id.XXX);
         Id=findViewById(R.id.Id);
        if(intent.getSerializableExtra("student")!=null){
            student=(Student)intent.getSerializableExtra("student");

            Name.setText(student.getName());
            Phone.setText(student.getPhone());
            Id.setText(student.getid());
            txtEmail.setText(FBref.refAuth.getCurrentUser().getEmail());
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}