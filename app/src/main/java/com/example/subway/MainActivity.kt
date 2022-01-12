package com.example.subway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // hide action bar
        supportActionBar?.hide()

        // get parameter from SelectionActivity
        val targetStation = intent.getStringExtra("keyStation")
        val lineUsed = intent.getStringExtra("keyLine")
        Log.d("tag", "targetStation: $targetStation, lineUsed: $lineUsed")
    }
}