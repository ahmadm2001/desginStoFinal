package com.example.desginstofinal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ConfirmList extends ArrayAdapter<String> {

    private ArrayList<Order> Order;
    int Layout;
    LayoutInflater layoutInflater;
    public ConfirmList(Context context, int Layout, ArrayList<Order>Order){
        super(context,Layout);
        this.Layout=Layout;
        this.Order=Order;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ConfirmList.ViewHolder viewHolder=null;

        if(convertView==null){

            viewHolder=new  ConfirmList.ViewHolder();

            convertView=layoutInflater.inflate(Layout,parent,false);
            TextView Name=(TextView)convertView.findViewById(R.id.col1);
            TextView NameProuct=(TextView)convertView.findViewById(R.id.col2);
            TextView startDate=(TextView)convertView.findViewById(R.id.col3);
            TextView endDate=(TextView)convertView.findViewById(R.id.col4);
            TextView OrderId=(TextView)convertView.findViewById(R.id.col5);
            ImageView imgDone=(ImageView) convertView.findViewById(R.id.ImgStatus);
            TextView guid=(TextView)convertView.findViewById(R.id.col6);
            viewHolder.Name=Name;
            viewHolder.NameProduct=NameProuct;
            viewHolder.Date=startDate;
            viewHolder.DateEnd=endDate;
            viewHolder.OrderId=OrderId;
            viewHolder.imgDone=imgDone;
            viewHolder.guid=guid;
            convertView.setTag(viewHolder);

        }
        else{
            viewHolder=(ConfirmList.ViewHolder) convertView.getTag();
        }

        viewHolder.Name.setText(Order.get(position).getStudent());
        viewHolder.NameProduct.setText(Order.get(position).getName());
        viewHolder.OrderId.setText("Order Id:"+Order.get(position).getOrderid());
        viewHolder.Date.setText("Start Date:"+Order.get(position).getDate());
        viewHolder.DateEnd.setText("End Date:"+Order.get(position).getEndDate());
        if(Order.get(position).isDone()){
            viewHolder.imgDone.setVisibility(View.VISIBLE);
        }
        viewHolder.guid.setText(Order.get(position).getGuid());
        return convertView;//super.getView(position, convertView, parent);


    }

    public  class  ViewHolder{
        TextView Name;
        TextView NameProduct;
        TextView Date;
        TextView Description;
        TextView DateEnd;
        TextView OrderId;
        ImageView imgDone;
        TextView guid;
    }
    @Override
    public int getCount() {
        return this.Order.size();
    }


    @Nullable
    @Override
    public String getItem(int position) {
        return this.Order.get(position).getName();
    }

}
