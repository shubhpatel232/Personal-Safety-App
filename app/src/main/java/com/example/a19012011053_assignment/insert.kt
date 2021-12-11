package com.example.a19012011053_assignment

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class insert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        val edname: EditText =findViewById(R.id.name)
        val edphone:EditText=findViewById(R.id.number)
        val savenm:Button=findViewById(R.id.insert)
        var helper=MyHelper(applicationContext)
        var db=helper.readableDatabase
        savenm.setOnClickListener {
            if (edname.text.toString() != "" && edphone.text.toString() != "") {
                if (edphone.text.length==10){
                    var cv = ContentValues()
                    cv.put("name", edname.text.toString())
                    cv.put("Phone", edphone.text.toString())
                    db.insert("info", null, cv)

                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("add record")
                    ad.setMessage("inserted successfully")
                    ad.setPositiveButton("ok", null)
                    ad.show()
                    edname.setText("")
                    edphone.setText("")
                    edname.requestFocus()
                }
                else{
                    Toast.makeText(this,"enter correct number", Toast.LENGTH_LONG).show()
                }

            }

            else{
                Toast.makeText(this,"enter the values", Toast.LENGTH_LONG).show()
            }
        }

    }
}