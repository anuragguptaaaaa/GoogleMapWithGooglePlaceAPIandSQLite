package com.anuragg.main_task;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    EditText et_type_of_emergency,et_name,et_p_no,et_address,et_land,et_pin;
    CheckBox ch_emergency,ch_police,ch_Fire;
    SQLiteDatabase db;
    int id;
    String lat_str;
    String lon_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i=getIntent();
        Bundle b=i.getExtras();
         lat_str=b.getString("lat1");
         lon_str=b.getString("lon1");

        et_type_of_emergency= (EditText) findViewById(R.id.editText);
        et_name= (EditText) findViewById(R.id.name);
        et_p_no= (EditText) findViewById(R.id.pno);
        et_address= (EditText) findViewById(R.id.addr);
        et_land= (EditText) findViewById(R.id.landmark);
        et_pin= (EditText) findViewById(R.id.pin);

        ch_emergency= (CheckBox) findViewById(R.id.ch1);
        ch_police= (CheckBox) findViewById(R.id.ch2);
        ch_Fire= (CheckBox) findViewById(R.id.ch3);

        db=new MyDatabase(this).getWritableDatabase();


       idnoGenerator();

    }
    public void idnoGenerator()
    {
        String qry="select max(idno) from details";
        Cursor c = db.rawQuery(qry,null);
        if (c.moveToFirst())
        {
            id = c.getInt(0);
            if (id == 0)
            {
                id = 101;
            }
            else
            {
                id++;
            }

        }
    }
    public void submitDetail(View v)
    {
        TextView ttt= (TextView) findViewById(R.id.tttt);
        String type_emergency=et_type_of_emergency.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        String phone=et_p_no.getText().toString().trim();
        String address=et_address.getText().toString().trim();
        String landmark=et_land.getText().toString().trim();
        String pin_no=et_pin.getText().toString().trim();
        String emergency="no";
        String police="no";
        String Fire_Station="no";

        if(ch_emergency.isChecked())
        {
            emergency="yes";
        }
        if(ch_police.isChecked())
        {
            police="yes";
        }
        if(ch_Fire.isChecked())
        {
            Fire_Station="yes";
        }

        //ttt.setText(lon_str+"\n"+lat_str);

        String qry="insert into details values("+id+",'"+type_emergency+"','"+name+"','"+phone+"','"+address+"','"+landmark+"','"+pin_no+"','"+emergency+"','"+police+"','"+Fire_Station+"','"+lat_str+"','"+lon_str+"')";
        db.execSQL(qry);
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
    public void onView(View v)
    {
        startActivity(new Intent(this,ViewDetail.class));
    }
}
