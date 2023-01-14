package com.example.desginstofinal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;


public class NewProductFragment extends Fragment {


    EditText text1,text2,text3,text4;
    Button btn1,btn2;



    public void addNew(String name,String description,String startdate,String enddate)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);//R.style.AppCompatAlertDialogStyle);

        builder.setTitle("New Procut?");
        builder.setMessage("are you sure?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                products products=new products(name,description,"",startdate,startdate,false);
              //  Toast.makeText(getActivity(),products.getName(),Toast.LENGTH_SHORT).show();
//                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance("https://alfaversion-3fc76-default-rtdb.firebaseio.com/");
//                DatabaseReference refpro=firebaseDatabase.getReference("products");
                FBref.refpro.child(products.getName()).setValue(products).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                       // Toast.makeText(getActivity(),String.valueOf(task.isSuccessful()),Toast.LENGTH_SHORT).show();
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"The product is stored!!",Toast.LENGTH_SHORT).show();

                            text1.setText("");
                            text2.setText("");
                            text3.setText("");
                            text4.setText("");
                        }
                        else
                        {
                  Toast.makeText(getActivity(), "Error Storing!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e!=null){
                            Toast.makeText(getActivity(),"exption!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        AlertDialog alert11 = builder.create();
        alert11.show();

    }


    private  void openDateDialog(){

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

                 String date=String.format("%s/%s/%s",i2,i1+1,i);

                 text3.setText(date);
                 try{

                     Date date1=simpleDateFormat.parse(date);

                     Calendar calendar1=Calendar.getInstance();
                     calendar1.setTime(date1);
                     calendar1.add(Calendar.DAY_OF_MONTH,7);
                     date1=calendar1.getTime();
                     text4.setText(simpleDateFormat.format(date1));
                 }
                 catch (Exception ex){

                 }
            }
        }, year,month,day).show();
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        try {
            View view = inflater.inflate(R.layout.fragment_new_product, null, false);

            text1 = view.findViewById(R.id.col1);
            text2 = view.findViewById(R.id.col2);
            text3 = view.findViewById(R.id.col3);
            text4 = view.findViewById(R.id.col4);

            btn1 = view.findViewById(R.id.btn10);
            btn2 = view.findViewById(R.id.btn11);


            text3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDateDialog();
                }
            });
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String t1 = String.valueOf(text1.getText().toString());
                    String t2 = String.valueOf(text2.getText().toString());
                    String t3 = String.valueOf(text3.getText().toString());
                    String t4 = String.valueOf(text4.getText().toString());
                    if (t1.isEmpty() || t2.isEmpty()) {

                        Toast.makeText(getActivity().getApplicationContext(), "row/(s) is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ///  Toast.makeText(getActivity(),t2,Toast.LENGTH_SHORT).show();
                    addNew(t1, t2, t3, t4);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    text4.setText("");
                }
            });
            return view;
        }
        catch (Exception ex){
            Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}