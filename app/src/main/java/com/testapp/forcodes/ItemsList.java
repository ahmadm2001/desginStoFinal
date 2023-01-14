package com.testapp.forcodes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ItemsList extends AppCompatActivity {
    RecyclerView itemsRecView;

    FloatingActionButton fItemsAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        setTitle("search here..");

        itemsRecView = (RecyclerView)findViewById(R.id.itemsRecView) ;
        itemsRecView.setLayoutManager(new LinearLayoutManager(this));

//        FirebaseDatabase options.(FirebaseDatabase.getInstance().getReference().child("items"),Item.class).build();

        fItemsAdd = (FloatingActionButton) findViewById(R.id.fItemsAdd);
        fItemsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddItems.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
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