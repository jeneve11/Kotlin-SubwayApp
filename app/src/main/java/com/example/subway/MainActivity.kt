package com.example.subway

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // declare fragments under MainActivity
    private var fragmentOne = FragmentOne()
    private var fragmentTwo = FragmentTwo()
    private var fragmentThree = FragmentThree()

    // get parameter from SelectionActivity

    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // hide action bar
        supportActionBar?.hide()

        val targetStation = intent.getStringExtra("keyStation")
        val line = intent.getStringExtra("keyLine")

        val bundle = Bundle()
        bundle.putString("targetStation", targetStation)
        bundle.putString("line", line)
        fragmentOne.arguments = bundle


        // Bottom Navigation
        initNavigationBar()

        // print out Target station and line
        Log.d("tag", "targetStation: $targetStation, line: $line")


    }

    private fun initNavigationBar() {
        bnv_main.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.first -> {
                        changeFragment(fragmentOne)
                    }
                    R.id.second -> {
                        changeFragment(fragmentTwo)
                    }
                    R.id.third -> {
                        changeFragment(fragmentThree)
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}