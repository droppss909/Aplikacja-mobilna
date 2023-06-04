package com.example.searchviewkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class firstActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        val checkClick=findViewById<ConstraintLayout>(R.id.first)
        checkClick.setOnClickListener {
            goToHome()
        }
    }

    fun goToHome(){
        val intent2= Intent(this,MainActivity::class.java)
        startActivity(intent2)
    }

}