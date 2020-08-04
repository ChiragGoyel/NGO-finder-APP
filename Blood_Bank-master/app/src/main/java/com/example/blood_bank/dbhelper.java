package com.example.blood_bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

public class dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mydataa46dgsese33497.db";
    public static final String TABLE_NAME = "Donors";
    public static final String COLOUMN_ID = "id";
    public static final String COLOUMN_NAME = "name";
    public static final String COLOUMN_GENDER = "gender";
    public static final String COLOUMN_EMAIL = "email";
    public static final String COLOUMN_PHONE = "phone";
    public static final String COLOUMN_ADDRESS = "address";
    public static final String COLOUMN_CITY = "city";
    public static final String COLOUMN_BLOODGROUP = "blood_group";
    public static final String COLOUMN_USERNAME = "username";
    public static final String COLOUMN_PASSWORD = "password";
    public static final String COLOUMN_DCOMP = "dcompleted";

    public static final String TABLE_NAME2 = "Patient";
    public static final String COLOUMN_PID = "pid";
    public static final String COLOUMN_TYPE = "type";
    public static final String COLOUMN_RELATION = "relation";
    public static final String COLOUMN_PNAME = "pname";
    public static final String COLOUMN_PBGROUP = "pblood_group";
    public static final String COLOUMN_CONTACT = "contact";
    public static final String COLOUMN_CONDITION = "condition";
    public static final String COLOUMN_PCOMP = "pcompleted";


    public static final String TABLE_NAME3 = "TranscationTable";
    public static final String COLOUMN_TID = "tid";
    public static final String COLOUMN_PATIENT = "pid";
    public static final String COLOUMN_DONOR = "id";


    public static Integer current_pid = 100;
    public static Integer current_id = 103250;
    public static Integer current_tid = 104460;

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, 25);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 +
                "(pid integer, type text,relation text,pname text,pblood_group text,contact text,condition text, pcompleted integer default 0)"
        );
        db.execSQL("create table " + TABLE_NAME +
                "(id integer ,name text,gender text,email text,phone text,address text,city text,blood_group text,username text,password text, dcompleted integer default 0)"
        );

        db.execSQL("create table " + TABLE_NAME3 +
                "(tid integer ,pid integer, id integer, pname text, dname text)"
        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
        //onCreate2(db);
    }

    public boolean insertDon(String name, String gender, String email, String phone, String address, String city, String blood_group, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("id", current_id);
        Random a = new Random();
        int b = a.nextInt(36336);
        current_id += 242 + b ;
        cv.put("gender", gender);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("address", address);
        cv.put("city", city);
        cv.put("blood_group", blood_group);
        cv.put("username", username);
        cv.put("password", password);
        cv.put("dcompleted", 0);
        long ins = db.insert("Donors", null, cv);
        if (ins == -1) return false;
        else return true;
    }


    public boolean insertTran(String pname, String dname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", 0);
        cv.put("pid", 0);
        cv.put("pname", pname);
        cv.put("dname", dname);
        cv.put("tid", current_tid);
        Random a = new Random();
        int b = a.nextInt(3636);
        current_tid += 536 + b;
        long ins = db.insert("TranscationTable", null, cv);

//        return true;


        ContentValues cv2 = new ContentValues();
        cv.put("dcompleted", 1);
        ContentValues cv3 = new ContentValues();
        cv.put("pcompleted", 1);

        db.execSQL("UPDATE Donors SET dcompleted=1 WHERE name like '"+dname+"'");
        db.execSQL("UPDATE Patient SET pcompleted=1 WHERE pname like '"+pname+"'");

//        long ins2 = db.update("Donors", cv2, "name="+dname, null);
//        long ins3 = db.update("Patient", cv3, "pname="+pname, null);
//
//        if (ins == -1 || ins2==-1 || ins3==-1)
//            return false;
//        else
            return true;


    }




    public String insertPatient(String type, String relation, String pname, String pblood_group, String contact, String condition) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pname", pname);
        cv.put("pid", current_pid);
        Random a  = new Random();
        int b = a.nextInt(763);
        current_pid += 234 + b;
        cv.put("type", "T");
        cv.put("relation", "R");
        cv.put("condition", condition);
        cv.put("contact", contact);
        cv.put("pblood_group", pblood_group);
        cv.put("pcompleted", 0);
        long ins = db.insert("Patient", null, cv);
        if (ins == -1) return "no";
        else return "yes";


    }



    public ArrayList<String> getAllDonors(String id) {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Donors where blood_group='" + id + "' and dcompleted=0", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_NAME)));
                res.moveToNext();
            }
        } catch (Exception es) {
            array_list.add(es.toString());


        }
        return array_list;
    }

    public ArrayList<String> getAllDonors() {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Donors where dcompleted=0", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_NAME)));
                res.moveToNext();
            }
        } catch (Exception es) {
            array_list.add(es.toString());

        }
        return array_list;
    }

    public ArrayList<String> getAllUsers() {

        ArrayList<String> array_list = new ArrayList<String>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from Patient where pcompleted=0", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(COLOUMN_PNAME)));
                res.moveToNext();
            }
        } catch (Exception es) {
            array_list.add(es.toString());
        }
        return array_list;
    }

    public Cursor getDon_details(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Donors where name = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }

    public Cursor getUser_details(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Patient where pname = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }

    public Cursor getDon_details1(String nm) {

        Cursor c = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            c = db.rawQuery("select * from Donors where name = '" + nm + "'", null);
        }
        catch (Exception e)
        {

        }
        return c;
    }
}

