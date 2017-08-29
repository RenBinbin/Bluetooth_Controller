package com.canny.renbinbin1.bluetooth_controller.bluetooth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ming on 2017/1/23.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BLUETOOTH = "create table bluetooth (id integer primary key " +
            "autoincrement,name text,message text)";


    public DatabaseHelper(Context context){
        super(context, "bluetooth.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BLUETOOTH);
        Log.d("datahelper","数据库创建了");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
