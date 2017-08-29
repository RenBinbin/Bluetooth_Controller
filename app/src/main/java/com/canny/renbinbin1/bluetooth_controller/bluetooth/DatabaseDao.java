package com.canny.renbinbin1.bluetooth_controller.bluetooth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Ming on 2017/1/23.
 */

public class DatabaseDao {

    private DatabaseHelper databaseHelper;
    private final SQLiteDatabase db;
    private ArrayList<BlueToothBean> bookBeanArrayList;

    public DatabaseDao(Context context){
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    //查询
    public ArrayList<BlueToothBean> query(){
        Cursor cursor = db.query("bluetooth", null, null, null, null, null, null);
        bookBeanArrayList = new ArrayList<>();
        if (cursor != null){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String message = cursor.getString(cursor.getColumnIndex("message"));

                BlueToothBean blueToothBean = new BlueToothBean();
                blueToothBean.setId(Integer.parseInt(id));
                blueToothBean.setName(name);
                blueToothBean.setMessage(message);
                bookBeanArrayList.add(blueToothBean);
            }
        }
        cursor.close();
        return bookBeanArrayList;
    }

    //插入
    public long insert(String message){
        ContentValues values = new ContentValues();
        BlueToothBean blueToothBean=new BlueToothBean();
//        values.put("name",blueToothBean.getName());
        values.put("message",blueToothBean.getMessage());
        return db.insert("bluetooth", null, values);
    }
    private static String byteToHexString(byte[] bytes) {
        String result="";
        for (int i = 0; i <bytes.length ; i++) {
            String hexString=Integer.toHexString(bytes[i]&0xFF);
            if(hexString.length()==1){
                hexString='0'+hexString;
            }
            result+=hexString.toUpperCase();
        }
        return result;
    }

    /**
     * 更新
     */
    public long update(BlueToothBean blueToothBean){
        ContentValues values = new ContentValues();
        values.put("name",blueToothBean.getName());
        values.put("message",blueToothBean.getMessage());
        values.put("id",blueToothBean.getId());
        return db.update("bluetooth", values, "id = ?", new String[]{blueToothBean.getId() + ""});
    }

    public long delete(int id){
        return db.delete("bluetooth","id = ?",new String[]{id+""});
    }

}
