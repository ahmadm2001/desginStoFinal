package com.example.desginstofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentListArchive  extends ArrayAdapter<String> {
    private ArrayList<Order>orders;
    int Layout;
    LayoutInflater layoutInflater;
    public  StudentListArchive(Context context,int Layout,ArrayList<Order>orders){
        super(context,Layout);
        this.Layout=Layout;
        this.orders=orders;
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
            TextView startDate=(TextView)convertView.findViewById(R.id.col2);
            TextView endDate=(TextView)convertView.findViewById(R.id.col3);
            viewHolder.Name=Name;
            viewHolder.Date=startDate;
            viewHolder.endDate=endDate;
            convertView.setTag(viewHolder);

        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.Name.setText(orders.get(position).getName());
        viewHolder.Date.setText(orders.get(position).getDate());
        viewHolder.endDate.setText(orders.get(position).getEndDate());

        return convertView;//super.getView(position, convertView, parent);


    }

    public  class  ViewHolder{
        TextView Name;
        TextView Date;
        TextView Description;
        TextView endDate;
        TextView id;
        TextView student;
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return orders.get(position).getId();
    }
}
