package com.testapp.forcodes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddItems extends AppCompatActivity {
    EditText add_name,add_count,add_details,purl;
    Button add_submit,add_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        add_name=(EditText) findViewById(R.id.add_name);
        add_count=(EditText) findViewById(R.id.add_count);
        add_details=(EditText) findViewById(R.id.add_details);
        purl = (EditText)findViewById(R.id.add_purl);

        add_back =(Button) findViewById(R.id.add_back);
        add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ItemsList.class));
                finish();
            }
        });

        add_submit=(Button) findViewById(R.id.add_submit);
        add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();
            }

            private void processinsert()
            {
                Map<String,Object> map=new HashMap<>();
                map.put("ItemName",add_name.getText().toString());
                map.put("count",add_count.getText().toString());
                map.put("details",add_details.getText().toString());
                map.put("purl",purl.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("items").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                add_name.setText("");
                                add_count.setText("");
                                add_details.setText("");
                                purl.setText("");
                                Toast.makeText(AddItems.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {

                                Toast.makeText(AddItems.this, "Could not insert", Toast.LENGTH_SHORT).show();

                            }
                        });
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