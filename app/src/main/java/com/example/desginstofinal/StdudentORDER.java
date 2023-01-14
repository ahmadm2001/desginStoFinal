package com.example.desginstofinal;

import static com.example.desginstofinal.FBref.refAuth;
import static com.example.desginstofinal.FBref.refpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StdudentORDER extends AppCompatActivity {

    ListAdapter adapter;
    SearchView searchView;
    ListView LV1;
    String[] LIST={};
    products p;
    ArrayList<String> details = new ArrayList<>();
    String name,Descripion;
    ArrayAdapter<String> adp;

    ArrayList<products> Detail = new ArrayList<>();

    String UID;
    LinearLayout layout;

    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdudent_order);
        LV1 = (ListView) findViewById(R.id.LV1);
        // LV1.setOnItemClickListener();
        LV1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        layout = new LinearLayout(this);

        LV1 = (ListView) findViewById(R.id.LV1);
//        LV1.setOnItemClickListener(StdudentORDER.this);
        LV1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        adp = new ArrayAdapter<String>(StdudentORDER.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);



        FirebaseUser firebaseUser = refAuth.getCurrentUser();


        //  FirebaseUser firebaseUser = refAuth.getCurrentUser();
        refpro.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                //  UID =  (String) ds.getKey();
                p = ds.getValue(products.class);
                name ="ahmad";
                Descripion = "good";
                LV1.setAdapter(adp);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Failed", "Failed to read value", databaseError.toException());
            }
        });
    }

    }

