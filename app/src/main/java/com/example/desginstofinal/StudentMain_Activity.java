package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StudentMain_Activity extends AppCompatActivity {
    Intent t;
    Button orderBTN;
    Button HistoryBTN;
    Button ProfileBTN;
    Button notificationBTN;
   // Intent intent=getIntent();
    Bundle bundle;//=intent.getExtras();
    Student student;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        bundle=getIntent().getExtras();
//        if(bundle==null)
//        Toast.makeText(getApplicationContext(),"null", Toast.LENGTH_SHORT).show();
        if(bundle!=null) {
          //  Toast.makeText(getApplicationContext(), "xxx", Toast.LENGTH_LONG).show();
        if (bundle.getSerializable("student") != null) {
               student = (Student)bundle.getSerializable("student");
            }

        }

        HistoryBTN=findViewById(R.id.HistoryBTN);
        orderBTN=(Button)findViewById(R.id.orderBTN);
        ProfileBTN=findViewById(R.id.ProfileBTN);
        notificationBTN=findViewById(R.id.notificationBTN);

        notificationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t=new Intent(StudentMain_Activity.this,StudentNotification.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                t.putExtras(bundle);
                startActivity(t);
            }
        });
        ProfileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                t=new Intent(StudentMain_Activity.this,profile.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                 t.putExtras(bundle);
                startActivity(t);

//                Intent i=new Intent(StudentMain_Activity.this,ProfileStudent.class);
//                i.putExtra("student",student);
//                startActivity(i);

            }
        });
        orderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMain_Activity.this, ListOrder.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        HistoryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMain_Activity.this,ListArchive.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student", student);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * Show menu options
         * <p>
         * @param menu
         */
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /**
         * Respond to the menu item selected
         * <p>
         * @param item
         */
        String s = item.getTitle().toString();
        t = new Intent(this, StudentMain_Activity.class);
        if (s.equals("Profile")) {
            t=new Intent(StudentMain_Activity.this,profile.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            t.putExtras(bundle);
            startActivity(t);
        }
        if (s.equals("Order")) {
            Intent intent=new Intent(StudentMain_Activity.this, ListOrder.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            intent.putExtras(bundle);

            startActivity(intent);
        }
        if (s.equals("History")) {
            Intent intent=new Intent(StudentMain_Activity.this,ListArchive.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            intent.putExtras(bundle);

            startActivity(intent);
        }
        if (s.equals("Notification")) {
            t=new Intent(StudentMain_Activity.this,StudentNotification.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            t.putExtras(bundle);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finish();
    }
}