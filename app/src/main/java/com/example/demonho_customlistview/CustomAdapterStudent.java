package com.example.demonho_customlistview;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapterStudent extends ArrayAdapter<Student> {
    Context context;
    int resource;
    ArrayList<Student> lstStudent;
    public CustomAdapterStudent(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.lstStudent=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_student_custom,parent,false);
            viewHolder= new ViewHolder();
            viewHolder.tvID=(TextView)convertView.findViewById(R.id.tvID);
            viewHolder.tvLop=(TextView)convertView.findViewById(R.id.tvLop);
            viewHolder.tvTen=(TextView)convertView.findViewById(R.id.tvTen);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Student st=lstStudent.get(position);
        viewHolder.tvID.setText(st.getMssv());
        viewHolder.tvTen.setText(st.getTen());
        viewHolder.tvLop.setText(st.getLop());
        return convertView;
    }
    public class ViewHolder{
        TextView tvID, tvTen, tvLop;
    }
}
