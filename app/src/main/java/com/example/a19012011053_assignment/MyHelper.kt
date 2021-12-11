package com.example.a19012011053_assignment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class MyHelper(context: Context):SQLiteOpenHelper(context,"info",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE INFO(_id integer primary key autoincrement,name TEXT,Phone TEXT)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}