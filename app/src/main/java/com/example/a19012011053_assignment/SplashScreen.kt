package com.example.a19012011053_assignment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val top:TextView=findViewById(R.id.TopTextView)
        val middle:TextView=findViewById(R.id.middleTextView)
        val bottom:TextView=findViewById(R.id.bottomTextView)
        val topani=AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val middleani=AnimationUtils.loadAnimation(this,R.anim.middle_animation)
        val bottomani=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        top.startAnimation(topani)
        middle.startAnimation(middleani)
        bottom.startAnimation(bottomani)

        val splashScreenTimeOut=2500
        val homeIntent=Intent(this@SplashScreen,MainActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        },splashScreenTimeOut.toLong())

    }
}