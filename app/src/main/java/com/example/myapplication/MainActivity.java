package com.example.myapplication;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Firebase variables

    private static final String[] METER_MAKE = new String[] {
            "SECURE", "ZENUS", "Others" };
    private Button submit;
    private AutoCompleteTextView meter_Make;
    private EditText meter_SerialN;
    private View meterV;
    private ListView submitView;
    private Button submitted_Data;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// FirebBase instance
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference;

// initiate variables :
        meter_Make = (AutoCompleteTextView) findViewById(R.id.mtr_mk_list_AutoC);
        meter_SerialN = (EditText) findViewById(R.id.mtr_SerialNo_ET);
        submit = findViewById(R.id.submit_button);
        submitView = findViewById(R.id.submit_LV);
        submitted_Data = findViewById(R.id.submitted_Data);

                submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String mSerialN = meter_SerialN.getText().toString();
        String mMake = meter_Make.getText().toString();

        HashMap<String , Object> meter_map = new HashMap<>();
        meter_map.put("meter_Make" , mMake);
        meter_map.put("serial_no" , mSerialN);
        if(mSerialN.isEmpty()){
            Toast.makeText(MainActivity.this, "No Serial No. entered", LENGTH_SHORT).show();
        }
        else{
           /* FirebaseDatabase.getInstance().getReference().child("meter").child("serial_no").setValue(mSerialN);
            FirebaseDatabase.getInstance().getReference().child("meter").child("meter_Make").setValue(mMake);*/

           FirebaseDatabase.getInstance().getReference().child("meter").push().updateChildren(meter_map);
       //    FirebaseDatabase.getInstance().getReference().child("meter").push().updateChildren(meter_map);
            Toast.makeText(MainActivity.this, "data submitted", LENGTH_SHORT).show();


        }
// set the edit text to blank
        meter_Make.setText("");
        meter_SerialN.setText("");
    }

});
                /* ArrayList<String> mtrAL = new ArrayList<>();
            ArrayAdapter<String> mtr_Adapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_view, mtrAL);
                submitView.setAdapter(mtr_Adapter);

                mDatabaseReference = mFirebaseDatabase.getReference().child("meter");
                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       *//* ArrayList<Meter_Detail_Class> mtrAL = new ArrayList<Meter_Detail_Class>();
                         Meter_Detail_Class snapObj = snapshot.getValue(Meter_Detail_Class.class);
                       // mtr_Adapter.add(snapObj);
                         mtrAL.add(snapObj);
                        ArrayAdapter<Meter_Detail_Class> mtr_Adapter = new ArrayAdapter<Meter_Detail_Class>(MainActivity.this,R.layout.mtr_detail_layout_adapter, mtrAL);

                        *//*

                    //    mtrAL.add(snapshot.getKey().toString() + snapshot.getValue().toString())

                        mtrAL.clear();


                        for(DataSnapshot dsnap: snapshot.getChildren()){
                            Meter_Detail_Class snapObj = dsnap.getValue(Meter_Detail_Class.class);
                          //  mtrAL.add(snapObj);

                           String txt = snapObj.getMeter_Make() + " : " + snapObj.getSerial_no();
                           mtrAL.add(txt);
                          //  mtrAL.add(dsnap.getKey().toString() + " : " + dsnap.getValue().toString());

                        }
                        mtr_Adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
*/


        // Text View for Auto Complete Dropdown
        ArrayAdapter<String> mtr_mk_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, METER_MAKE);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.mtr_mk_list_AutoC);
        textView.setAdapter(mtr_mk_adapter);

        //Get auto complete value and edit text value and update in database

        // Try with simple custom adapter

        ArrayList<Meter_Detail_Class> mtrAL = new ArrayList<Meter_Detail_Class>();
        Meter_Adapter mtr_Adapter = new Meter_Adapter(this, mtrAL );
        submitView.setAdapter(mtr_Adapter);

        mDatabaseReference = mFirebaseDatabase.getReference().child("meter");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mtrAL.clear();

                for(DataSnapshot dsnap: snapshot.getChildren()){
                    Meter_Detail_Class snapObj = dsnap.getValue(Meter_Detail_Class.class);
                 //   mtrAL.add(snapObj);
                    mtr_Adapter.add(snapObj);

                }
                mtr_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

     // Submitted Data

        submitted_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });


           }
}