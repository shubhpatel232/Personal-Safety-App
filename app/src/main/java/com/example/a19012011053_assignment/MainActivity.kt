package com.example.a19012011053_assignment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {
    private lateinit var sendloc: Button
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edmessage:EditText=findViewById(R.id.sendmessage)
        val send:Button=findViewById(R.id.send)
        val addcnt:Button=findViewById(R.id.addcont)
        val deletecnt:Button=findViewById(R.id.delete)
        val showcnt:Button=findViewById(R.id.show)
        sendloc=findViewById(R.id.sendlocation)
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),10)
        }


        send.setOnClickListener {
            var helper = MyHelper(applicationContext)
            var db = helper.readableDatabase
            var rs = db.rawQuery("SELECT * FROM info", null)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 10)
            } else {
                if (rs.count == 0) {
                    Toast.makeText(this, "No contact added", Toast.LENGTH_LONG).show()
                } else {
                    if (edmessage.text.toString() == "") {
                        edmessage.setText("help!")
                    }
                    while (rs.moveToNext()) {
                        var obj = SmsManager.getDefault()
                        obj.sendTextMessage(
                            rs.getString(2),
                            null,
                            edmessage.text.toString(),
                            null,
                            null
                        )
                    }
                    Toast.makeText(this, "message send", Toast.LENGTH_LONG).show()
                }
            }
        }
        sendloc.setOnClickListener {
            checkPermissions()
        }


        addcnt.setOnClickListener {
            val intent:Intent=Intent(this,insert::class.java)
            startActivity(intent)
        }
        deletecnt.setOnClickListener {
            val intent:Intent=Intent(this,delete::class.java)
            startActivity(intent)
        }
        showcnt.setOnClickListener {
            val intent:Intent=Intent(this,show::class.java)
            startActivity(intent)
        }

    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1)
        }
        else{
            getLocations()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocations() {
        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {

            if (it==null){
                Toast.makeText(this,"can't get Location",Toast.LENGTH_SHORT).show()
            }
            else it.apply {
                var helper = MyHelper(applicationContext)
                var db = helper.readableDatabase
                var rs = db.rawQuery("SELECT * FROM info", null)
                if (rs.count == 0) {
                    Toast.makeText(applicationContext, "No contact added", Toast.LENGTH_LONG).show()
                } else {
                    while (rs.moveToNext()) {
                        var obj = SmsManager.getDefault()
                        obj.sendTextMessage(
                            rs.getString(2),
                            null,
                            "my loacation is ${it.latitude} ${it.longitude}",
                            null,
                            null
                        )
                    }
                    Toast.makeText(applicationContext, "message send", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1){
            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show()
                    getLocations()
                }
                else
                {
                    Toast.makeText(this,"permission not granted",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
