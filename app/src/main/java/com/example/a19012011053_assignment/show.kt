package com.example.a19012011053_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView

class show : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        val listview:ListView=findViewById(R.id.list)
        val showtext:TextView=findViewById(R.id.showtext)
        var helper=MyHelper(applicationContext)
        var db=helper.readableDatabase
        var rs=db.rawQuery("SELECT * FROM info",null)
        showtext.setText("You have added ${rs.count} Numbers")
        var adapter=SimpleCursorAdapter(applicationContext,android.R.layout.simple_expandable_list_item_2,rs,
            arrayOf("name","Phone"),
            intArrayOf(android.R.id.text1,android.R.id.text2),0)
        listview.adapter=adapter

    }
}