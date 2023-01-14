package com.example.desginstofinal;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListConfirmFragment extends Fragment {

    Student student;
    Bundle bundle;

    ListView listView;
    ConfirmList confirmList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_list_confirm, null, false);
        listView=view.findViewById(R.id.listfragment);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(view!=null){
                    ImageView imageView=view.findViewById(R.id.ImgStatus);
                    TextView ProductName=view.findViewById(R.id.col2);
                    TextView OrderId=view.findViewById(R.id.col5);
                    TextView txtGuid=view.findViewById(R.id.col6);
                    String id=OrderId.getText().toString().split(":")[1];
                    String key=txtGuid.getText().toString();
                   // Toast.makeText(getActivity(),id,Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(), android.app.AlertDialog.THEME_HOLO_LIGHT);
                    if(imageView.getVisibility()==View.VISIBLE){
                       // Toast.makeText(getActivity(),"The Product Not Available",Toast.LENGTH_SHORT).show();




                        builder.setTitle("?");
                        builder.setMessage("Do you want to return the product");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();

                                FBref.refpro.child(String.valueOf(ProductName.getText())).child("id").setValue("");
                                FBref.refpro.child(String.valueOf(ProductName.getText())).child("flag").setValue(false);

                             //   Query query=FBref.reford.orderByChild("orderid").equalTo(Double.parseDouble(id));
                             //   FBref.reford.child(id).removeValue();


                                FBref.reford.child(key).removeValue();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayList.clear();
                                        confirmList.notifyDataSetChanged();
                                    }
                                });
                                //
                                Toast.makeText(getActivity(),"The product has been returned",Toast.LENGTH_SHORT).show();

//                                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                                        String key="";
//                                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//
//                                            key=dataSnapshot.getRef().getKey();
//                                            break;
//                                        }
//
//                                    //    Toast.makeText(getActivity(),key,Toast.LENGTH_SHORT).show();
//                                        FBref.reford.child(key).removeValue();
//                                        // confirmList.notifyDataSetChanged();
//                                        Toast.makeText(getActivity(),"The product has been returned",Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });

                                // Toast.makeText(getApplicationContext(),String.valueOf(ProductName.getText()),Toast.LENGTH_SHORT).show();
                               // imageView.setVisibility(View.VISIBLE);

                               // FBref.refpro.child(String.valueOf(ProductName.getText())).child("flag").setValue(true);
                                // FBref.reford.child(id).child("done").setValue(true);


                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });


                        builder.show();


                        return;
                    }



                    builder.setTitle("?");
                    builder.setMessage("send request");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();

                            // Toast.makeText(getApplicationContext(),String.valueOf(ProductName.getText()),Toast.LENGTH_SHORT).show();
                            imageView.setVisibility(View.VISIBLE);

                            FBref.refpro.child(String.valueOf(ProductName.getText())).child("flag").setValue(true);

                            FBref.reford.child(key).child("done").setValue(true);

                          //  Query query=FBref.reford.orderByChild("orderid").equalTo(Double.parseDouble(id));
                            //limitToFirst(1);

                           // DatabaseReference databaseReference= query.getRef();


                         //   databaseReference.setValue()]
                           // if(key!=null)
                           // Toast.makeText(getActivity(),key,Toast.LENGTH_SHORT).show();
//                            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    String key="";
//                                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                                        //dataSnapshot.getRef().child(true);
//                                       key=dataSnapshot.getRef().getKey();
//                                       break;
//                                      //  dataSnapshot.getRef().child(key).child("done").setValue(true);
//                                      //  Toast.makeText(getActivity(),key,Toast.LENGTH_SHORT).show();
//                                    }
//                                    FBref.reford.child(key).child("done").setValue(true);
//                                   // Toast.makeText(getActivity(),key,Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(getActivity(),"The order has been confirmed",Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                }
//                            });
                           // FBref.reford.child(id).child("done").setValue(true);


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
            }
        });
       try{

           getData();
           return view;
       }
       catch (Exception ex){
        return  null;
       }

    }

    ArrayList<Order> arrayList = new ArrayList<>();
    public  void getData(){







        firebase firebase=new firebase() {
            @Override
            public void onstart() {




//
             if (FBref.reford != null) {
              //   Toast.makeText(getActivity(),String.valueOf(arrayList.size()),Toast.LENGTH_SHORT).show();
                    FBref.reford.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try {
                                arrayList.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                    Order pp = (Order) dataSnapshot.getValue(Order.class);
                                    if (pp != null) {
//                                        try {
//                                            // if(pp.getDescription()!=null)
//                                            //  Toast.makeText(getApplicationContext(),pp.getDescription(),Toast.LENGTH_SHORT).show();
//                                        } catch (Exception ex) {
//                                            // Toast.makeText(getActivity(), "error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
                                          arrayList.add(pp);
                                    }
                                }

                                //
                                //  Toast.makeText(getActivity(),String.valueOf(arrayList.size()),Toast.LENGTH_SHORT).show();

                                if(arrayList.size()>0)
                                  onfinish(arrayList);
                                else {
                                    Toast.makeText(getActivity(),"No Data!!",Toast.LENGTH_SHORT).show();
//                                    if(confirmList.getCount()==0){
//
//                                    }
//                                    else {
//                                        //  Toast.makeText(getActivity(),"OK!!",Toast.LENGTH_SHORT).show();
//                                    }
                                }
                            }
                            catch (Exception ex){


                                if(getActivity()!=null)
                                Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            if (error != null) {
                                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else {
                             //   Toast.makeText(getActivity(),String.valueOf(arrayList.size()),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


             }

            }

            @Override
            public void onfinish(ArrayList<Order> _order) {
                //  Toast.makeText(getApplicationContext(),String.valueOf(_products.size()),Toast.LENGTH_SHORT).show();


                confirmList=new ConfirmList(getActivity(),R.layout.row_item_confirm,_order);
                listView.setAdapter(confirmList);
                // Toast.makeText(getApplicationContext(),String.valueOf(listOrder.getCount()),Toast.LENGTH_SHORT).show();


            }
        };

        firebase.onstart();


    }


  private   interface  firebase{
        public  void  onstart();
        public  void  onfinish(ArrayList<Order> _order);
    }
}