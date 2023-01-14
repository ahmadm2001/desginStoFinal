package com.testapp.forcodes;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Button ItemsBtn;
    Button StudentLstBtn;
    Button profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        ItemsBtn= findViewById(R.id.ItemsBtn);
        ItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,ItemsList.class));

            }
        });

        profileBtn =findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Home.this,ProfileEdit.class));

            }
        });


        StudentLstBtn = findViewById(R.id.StudentLstBtn);
        StudentLstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,StudentList.class));


            }
        });




    }



    //    public void btnInsert(View view){
//        Intent intent = new Intent(Home.this,Student_List.class);
//        startActivity(intent);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, ProfileEdit.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent1 = new Intent(this, ItemsList.class);
                startActivity(intent1);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(this,StudentList.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                Intent intent4= new Intent(this,Home.class);
                startActivity(intent4);
                return true;
            case R.id.item5:
                Toast.makeText(this, "Bye Bye...", Toast.LENGTH_SHORT).show();
                Intent intent5= new Intent(this,Login.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}