package com.example.a19012011053_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        val edphone: EditText =findViewById(R.id.number)
        val deletenm: Button =findViewById(R.id.delete)
        val deleteall:Button=findViewById(R.id.deleteall)

        var helper=MyHelper(applicationContext)
        var db=helper.readableDatabase
        deletenm.setOnClickListener {
            if(edphone.text.toString()!="") {
                db.delete("info", "Phone = ?", arrayOf(edphone.text.toString()))
                var ad = AlertDialog.Builder(this)
                ad.setTitle("delete record")
                ad.setMessage("delete successfully")
                ad.setPositiveButton("ok", null)
                ad.show()
                edphone.setText("")
                edphone.requestFocus()
            }
            else{
               Toast.makeText(this,"enter the value",Toast.LENGTH_LONG).show()
            }
        }
        deleteall.setOnClickListener {
            db.delete("info", null,null)
            var ad = AlertDialog.Builder(this)
            ad.setTitle("delete record")
            ad.setMessage("delete successfully")
            ad.setPositiveButton("ok", null)
            ad.show()
        }
    }
}