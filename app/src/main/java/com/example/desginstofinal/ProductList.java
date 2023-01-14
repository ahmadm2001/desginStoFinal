package com.example.desginstofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductList extends ArrayAdapter<String> {
    private ArrayList<products>products;
    int Layout;
    LayoutInflater layoutInflater;
    public ProductList(Context context, int Layout, ArrayList<products>products){
        super(context,Layout);
        this.Layout=Layout;
        this.products=products;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;

        if(convertView==null){

            viewHolder=new ViewHolder();

            convertView=layoutInflater.inflate(Layout,parent,false);
            TextView Name=(TextView)convertView.findViewById(R.id.col1);
            TextView Decription=(TextView)convertView.findViewById(R.id.col2);
            TextView startDate=(TextView)convertView.findViewById(R.id.col3);
            TextView endDate=(TextView)convertView.findViewById(R.id.col4);
            ImageView imgStatus=(ImageView)convertView.findViewById(R.id.ImgStatus);
            viewHolder.Name=Name;
            viewHolder.Description=Decription;
            viewHolder.Date=startDate;
            viewHolder.DateEnd=endDate;
            viewHolder.img=imgStatus;
            convertView.setTag(viewHolder);

        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.Name.setText(products.get(position).getName());
        viewHolder.Description.setText(products.get(position).getDescription());
        if(products.get(position).getid().isEmpty()==false){
            viewHolder.img.setVisibility(View.VISIBLE);
        }
       // viewHolder.Date.setText(products.get(position).getStartDate());
       // viewHolder.DateEnd.setText(products.get(position).getEndDate());
        return convertView;//super.getView(position, convertView, parent);


    }

    public  class  ViewHolder{
        TextView Name;
        TextView Date;
        TextView Description;
        TextView DateEnd;
        ImageView img;
    }
    @Override
    public int getCount() {
        return this.products.size();
    }


    @Nullable
    @Override
    public String getItem(int position) {
        return this.products.get(position).getName();
    }
}
