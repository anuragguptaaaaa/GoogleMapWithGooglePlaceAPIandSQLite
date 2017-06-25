package com.anuragg.main_task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Anurag_305 on 13-May-17.
 */

public class MyDatabase extends SQLiteOpenHelper{

Context context;
    public MyDatabase(Context context) {
        super(context, "main_branch", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table details (idno number primary key,etype text,name text,pno text,address text,landmark text,pin text,emergency text,police text,fire text,lat text,lon text)";
        sqLiteDatabase.execSQL(qry);
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
