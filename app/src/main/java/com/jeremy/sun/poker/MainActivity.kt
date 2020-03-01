package com.jeremy.sun.poker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_test?.setOnClickListener {
            Toast.makeText(this@MainActivity, "start test", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, CalculateService::class.java)
            startService(intent)
        }
    }

}
