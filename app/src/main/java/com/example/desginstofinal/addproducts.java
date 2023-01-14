package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addproducts extends AppCompatActivity {
    EditText proDescription,eTname,eTid;
    Button btn;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String Description,name,id;
    boolean flag=false;
    DatabaseReference mDatabase;
    FrameLayout frameLayout;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproducts);
       frameLayout=findViewById(R.id.fragmentview);
      bottomNavigationView=findViewById(R.id.bottomNavigationView);

      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {

              switch (item.getItemId()){

                  case R.id.add:
                      changeFragment(new NewProductFragment());
                      break;

                  case  R.id.order:
                      changeFragment(new ListConfirmFragment());
                      break;
              }
              item.setChecked(true);
              return false;
          }
      });
        getSupportActionBar().hide();
//        proDescription=findViewById(R.id.proDescription);
//        eTname=findViewById(R.id.proname);
//        eTid=findViewById(R.id.idET);
//        btn=findViewById(R.id.btn10);
         changeFragment(new NewProductFragment());

    }


    void changeFragment(Fragment fragment) {
        try {
           // getSupportFragmentManager().popBackStackImmediate();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragmentview, fragment,null);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(addproducts.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    //    public void up(View view) {
//
//        Description=proDescription.getText().toString();
//        name=eTname.getText().toString();
//        id=eTid.getText().toString();
//        Products=new products(name,Description,id,flag);
//        refpro.child(name).setValue(Products);
//
//    }
}