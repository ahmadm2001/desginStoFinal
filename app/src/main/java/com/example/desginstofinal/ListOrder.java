package com.example.desginstofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.util.CollectionUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ListOrder extends AppCompatActivity {


    Student student;
    Bundle bundle;
    ListView listView;
    ProductList listOrder;
    double count=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        bundle=getIntent().getExtras();
        if(bundle.getSerializable("student")!=null){
            student=(Student) bundle.getSerializable("student");
            //Toast.makeText(getApplicationContext(),student.getName(),Toast.LENGTH_SHORT).show();
        }
        listView=(ListView) findViewById(R.id.listvieworeder);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(view!=null){
                    ImageView imageView=view.findViewById(R.id.ImgStatus);
                    TextView ProductName=view.findViewById(R.id.col1);

                    if(imageView.getVisibility()==View.VISIBLE){
                        Toast.makeText(getApplicationContext(),"The Product Not Available",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                    Date date,endDate;
                    try{
                        Calendar calendar=Calendar.getInstance();
                        date=calendar.getTime();
                        calendar.setTime(date);
                        calendar.add(Calendar.DAY_OF_MONTH,7);
                        endDate=calendar.getTime();


                        AlertDialog.Builder builder=new AlertDialog.Builder(ListOrder.this, android.app.AlertDialog.THEME_HOLO_LIGHT);

                        builder.setTitle("?");
                        builder.setMessage("send request");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                               // Toast.makeText(getApplicationContext(),String.valueOf(ProductName.getText()),Toast.LENGTH_SHORT).show();
                                imageView.setVisibility(View.VISIBLE);

                                String guid=String.valueOf(UUID.randomUUID());

                                FBref.refpro.child(String.valueOf(ProductName.getText())).child("id").setValue(student.getid());
                                // FBref.refpro.child(String.valueOf(ProductName.getText())).child("flag").setValue(false);


                                Order order=new Order(String.valueOf(ProductName.getText()),student.getName(),student.getid().trim(),simpleDateFormat.format(date),simpleDateFormat.format(endDate),false,guid,count);


                                try {
                                    FBref.reford.child(guid).setValue(order);
                                }
                                catch (Exception ex){
                                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();

                                }

                                Toast.makeText(getApplicationContext(),"We will send a request for confirmation",Toast.LENGTH_SHORT).show();

//                                Query query= FBref.reford.orderByChild("orderid");
//
//                                  if(query!=null){
//                                      try {
//
//                                          query.addListenerForSingleValueEvent(new ValueEventListener() {
//                                              @Override
//                                              public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
////                                                  for (DataSnapshot dataSnapshot: snapshot.getChildren()){
////                                                      count++;
////                                                  }
////                                                 // count=snapshot.getChildrenCount();
////                                                  count++;
//
//
//                                                  //String.valueOf(count)
//                                                  try {
//
//
//                                                     /// Toast.makeText(getApplicationContext(),order.getGuid(),Toast.LENGTH_SHORT).show();
//
//
//                                                  }
//                                                  catch (Exception exception){
//                                                      Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
//                                                  }
//                                                  //listOrder.notifyDataSetChanged();
//                                                  //   Toast.makeText(getApplicationContext(), String.valueOf(count), Toast.LENGTH_SHORT).show();
//                                              }
//
//                                              @Override
//                                              public void onCancelled(@NonNull DatabaseError error) {
//
//                                              }
//
//
//                                          });
//
//
//
//
//                                          // count=(int)query.get().getResult().getChildrenCount()+1;
//                                          // FBref.reford.child(String.valueOf(UUID.randomUUID())).setValue(order);
//
//
//                                      }
//                                      catch (Exception ex){
//                                          count=1;
//                                      }
//
//                                  }


//                                FBref.refpro.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        arrayList.clear();
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });

                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });


                        builder.show();

                    }
                    catch (Exception e){

                    }
                    //Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                }
            }
        });

        getData();
    }


    ArrayList<products>arrayList=new ArrayList<>();
    public  void getData(){


        firebase firebase=new firebase() {
            @Override
            public void onstart() {







                FBref.refpro.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        arrayList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            products pp=(products) dataSnapshot.getValue(products.class);
                            if(pp!=null){
                              try {
                                 // if(pp.getDescription()!=null)
                                //  Toast.makeText(getApplicationContext(),pp.getDescription(),Toast.LENGTH_SHORT).show();
                              }
                              catch (Exception ex){
                                  Toast.makeText(getApplicationContext(),"error:"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                              }


                            //  if(!arrayList.contains(pp))
                                arrayList.add(pp);
                            }
                        }

                     //   Toast.makeText(getApplicationContext(),String.valueOf(arrayList.size()),Toast.LENGTH_SHORT).show();
                        onfinish(arrayList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        if(error!=null){
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

            @Override
            public void onfinish(ArrayList<products> _products) {
              //  Toast.makeText(getApplicationContext(),String.valueOf(_products.size()),Toast.LENGTH_SHORT).show();
                listOrder=new ProductList(ListOrder.this,R.layout.row_item_order,_products);
                listView.setAdapter(listOrder);
               // Toast.makeText(getApplicationContext(),String.valueOf(listOrder.getCount()),Toast.LENGTH_SHORT).show();
                if(listOrder.getCount()==0){
                    Toast.makeText(getApplicationContext(),"No Data!!",Toast.LENGTH_SHORT).show();
                }
                else {
                   // Toast.makeText(getApplicationContext(),"OK!!",Toast.LENGTH_SHORT).show();
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
        public  void  onstart();
        public  void  onfinish(ArrayList<products>_products);
    }
}