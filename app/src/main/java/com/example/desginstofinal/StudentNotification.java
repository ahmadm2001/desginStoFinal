package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentNotification extends AppCompatActivity {
    Bundle bundle;
    Intent i;
    Student student;

    TextView header;
    TextView confirmed;
    TextView holdon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notification);

        confirmed=findViewById(R.id.a);
        holdon=findViewById(R.id.b);
        header=findViewById(R.id.message);
        i=getIntent();
        bundle=i.getExtras();

        if(bundle.getSerializable("student")!=null) {

                student = (Student)bundle.getSerializable("student");

        }

        getData();
    }
    int c1=0,c2=0,c3=0;

    String pp,co,ho;
    private  void getData(){
       // Toast.makeText(getApplicationContext(),"ddd",Toast.LENGTH_SHORT).show();
         pp=header.getText().toString();
         co=confirmed.getText().toString();
         ho=holdon.getText().toString();
        // Toast.makeText(getApplicationContext(),student.getid(),Toast.LENGTH_SHORT).show();
        Query query=FBref.reford.orderByChild("id").equalTo(student.getid().trim());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // c1=(int)snapshot.getChildrenCount();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Order order=(Order) dataSnapshot.getValue(Order.class);
                    if(order!=null){



                     //   Toast.makeText(getApplicationContext(),order.getStudent(),Toast.LENGTH_SHORT).show();
                       // Toast.makeText()
                      //  Toast.makeText(getApplicationContext(),order.getGuid(),Toast.LENGTH_SHORT).show();
                     //   Toast.makeText(getApplicationContext(),String.valueOf(Boolean.parseBoolean(String.valueOf(order.getDone()))),Toast.LENGTH_LONG).show();
                        c1++;
                        if(order.getDone()){
                            c2++;
                        }
                        else
                        if(order.getDone()==false){
                            c3++;
                        }
                    }
                }

                pp=pp.replace("0",String.valueOf(c1));
                co=co.replace("0",String.valueOf(c2));
                ho=ho.replace("0",String.valueOf(c3));
                header.setText(pp);
                confirmed.setText(co);
                holdon.setText(ho);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}