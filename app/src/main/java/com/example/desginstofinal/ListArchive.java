package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListArchive extends AppCompatActivity {



    Student student;
    Bundle bundle;
    StudentListArchive adapter;
    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_archive);
        bundle=getIntent().getExtras();
        if(bundle.getSerializable("student")!=null){
            student=(Student) bundle.getSerializable("student");
        }
        listView=findViewById(R.id.listview1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        getData();
    }
    public  void getData(){

        firebase firebase=new firebase() {
            @Override
            public void onstart() {


                ArrayList<Order>__orders=new ArrayList<>();
                Query query=FBref.reford.child("id").equalTo(student.getid());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                            com.example.desginstofinal.Order pp=(com.example.desginstofinal.Order) dataSnapshot.getValue(com.example.desginstofinal.Order.class);
                            if(pp!=null){
                                __orders.add(pp);
                            }
                        }

                        onfinish(__orders);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onfinish(ArrayList<Order> _order) {

                adapter=new StudentListArchive(ListArchive.this,R.layout.row_item_archive,_order);
                listView.setAdapter(adapter);

                if(adapter.getCount()==0){
                    Toast.makeText(getApplicationContext(), "No Data Archive", Toast.LENGTH_SHORT).show();
                }

            }
        };
        firebase.onstart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      finish();

    }

    interface  firebase{
        public void onstart();
        public  void onfinish(ArrayList<Order>_order);
    }
}