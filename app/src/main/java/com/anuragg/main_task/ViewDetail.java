package com.anuragg.main_task;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDetail extends AppCompatActivity {
    ListView lv;
    SQLiteDatabase db;
    ArrayList al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList();

        db = new MyDatabase(this).getWritableDatabase();
        String qry = "select idno from details";
        Cursor c = db.rawQuery(qry, null);

        if (c.moveToFirst()) {
            do {
                int idno = c.getInt(0);
                al.add(idno);
            } while (c.moveToNext());
            ArrayAdapter aa=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,al);
            lv.setAdapter(aa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String qry="select etype,name,pno,address,landmark,pin,emergency,police,fire,lat,lon from details where idno="+al.get(i);
                    //(idno number primary key,name text,age text,contact text,email text,password text,state text)";

                    Cursor c=db.rawQuery(qry,null);
                    c.moveToFirst();
                    String e_type=c.getString(0);
                    String _name=c.getString(1);
                    String pno=c.getString(2);
                    String address=c.getString(3);
                    String landmark=c.getString(4);
                    String pin_no=c.getString(5);
                    String _emergency=c.getString(6);
                    String police=c.getString(7);
                    String fire=c.getString(8);
                    String lat=c.getString(9);
                    String lon11=c.getString(10);


                    String message="id number->"+e_type+"\nName->"+_name+"\nPhone Number->"+pno+"\nAddress->"+address+"\nLandMark->"+landmark+"\nPin Code->"+pin_no+"\n\nAlert_type\n\nEmergency->"+_emergency+"\nPolice->"+police+"\nFire Station->"+fire+"\n\nLocation Detail->\n\nLatitude->"+lat+"\nLongitute->"+lon11;
                    new AlertDialog.Builder(ViewDetail.this)
                            .setMessage(message)
                            .setIcon(R.drawable.common_google_signin_btn_icon_dark)
                            .setTitle("Detail")
                            .create()
                            .show();
                }
            });
        }
        else {
            Toast.makeText(this, "No Data Found ", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
