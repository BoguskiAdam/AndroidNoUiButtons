package com.example.adam.nohome

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goNextView(view: View) {
        val intent = Intent(this, NoUiActivity::class.java).apply { }
        startActivity(intent)
    }
}
