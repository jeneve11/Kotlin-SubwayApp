package com.example.subway

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.subway.databinding.ActivitySelectionBinding
import kotlinx.android.synthetic.main.activity_selection.autoCompleteTextView
import kotlinx.android.synthetic.main.activity_selection.autoCompleteTextView2

class SelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectionBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        binding = ActivitySelectionBinding.inflate(layoutInflater)

        // hide action bar
        supportActionBar?.hide()

        // set default dropdown menu
        val itemSubwayLine = resources.getStringArray(R.array.subwayLines)
        val arrayAdapterSubwayLine = ArrayAdapter(this, R.layout.subwayline_item, itemSubwayLine)
        binding.autoCompleteTextView.setAdapter(arrayAdapterSubwayLine)

        val itemSubwayStation = resources.getStringArray(R.array.line_total)
        val arrayAdapterSubwayStation = ArrayAdapter(this, R.layout.subwayline_item, itemSubwayStation)
        binding.autoCompleteTextView2.setAdapter(arrayAdapterSubwayStation)


        // when button is clicked, move to MainActivity
        binding.button.setOnClickListener {
            val subwayLine = autoCompleteTextView.text
            val subwayStation = autoCompleteTextView2.text
            Log.d("tag", "button Clicked, Target Line: $subwayLine, Target Station: $subwayStation")


            // passing two parameters
            val intent = Intent(this@SelectionActivity, MainActivity::class.java)
            intent.putExtra("keyLine", "$subwayLine")
            intent.putExtra("keyStation", "$subwayStation")
            startActivity(intent)
        }

        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        // onResume ??? ????????? ?????? ????????? -> ?????? ?????? ???????????? ?????????
        // ex) ??? ????????? ?????? ???????????? ?????? ?????? ??? ??? ?????? ?????????
        super.onResume()


        // ??????(subwayStation ????????? ?????? subwayLine ??????) ?????? ?????? ??????
        // subwayLine ????????? ?????? subwayStation dropdown menu ??????
        autoCompleteTextView.setOnItemClickListener { adapterView, view, position, rowID ->
            Log.d("tag", "position: $position, rowID: $rowID, string: ${adapterView.getItemAtPosition(position)}")
            val line = when {
                position == 0 -> R.array.line_1
                position == 1 -> R.array.line_2
                position == 2 -> R.array.line_3
                position == 3 -> R.array.line_4
                position == 4 -> R.array.line_5
                position == 5 -> R.array.line_6
                position == 6 -> R.array.line_7
                position == 7 -> R.array.line_8
                position == 8 -> R.array.line_9
                else -> R.array.line_total
            }
            val itemSubwayStation = resources.getStringArray(line)
            val arrayAdapterSubwayStation = ArrayAdapter(this, R.layout.subwayline_item, itemSubwayStation)
            autoCompleteTextView2.setAdapter(arrayAdapterSubwayStation)

            // Make button visible after subwayLine is decided
            binding.button.visibility = View.VISIBLE
            binding.button.isEnabled = true
        }
    }
}