package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;

import java.util.ArrayList;
import java.util.List;

public class Meter_Adapter extends ArrayAdapter<Meter_Detail_Class> {

    // Constructor
    /*public Meter_Adapter(Activity context, ArrayList<String> object) {
        super(context, 0, object);
    }*/

    public Meter_Adapter(@NonNull Context context, @NonNull List<Meter_Detail_Class> objects) {
        super(context, 0, objects);
    }

    /*public Meter_Adapter(@NonNull Context context, ArrayList<Meter_Detail_Class> resource) {
        super(context, 0, resource);
    }*/



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       if(convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.mtr_detail_layout_adapter,parent,false);
        //   convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.mtr_detail_layout_adapter, parent, false);
       }
// get the item of
        Meter_Detail_Class meter_detail_object = getItem(position);
          TextView textView_meterMake = (TextView) convertView.findViewById(R.id.textView_meterMake);
          TextView textView_SerialNo = (TextView) convertView.findViewById(R.id.textView_SerialNo);
// set the value

       textView_meterMake.setText(meter_detail_object.getMeter_Make());

        textView_SerialNo.setText(meter_detail_object.getSerial_no());

// return the view
return  convertView;
    }
}
