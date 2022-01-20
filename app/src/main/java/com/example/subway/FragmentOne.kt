package com.example.subway

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.subway.databinding.FragmentOneBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_one.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentOne : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        val targetStation = arguments?.getString("targetStation")
        val line = arguments?.getString("line")

        refreshText(targetStation)
        refreshLogo(line)
        refreshTime()
        refreshGPS()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        binding.refreshButton.setOnClickListener {
            val targetStation = arguments?.getString("targetStation")
            val line = arguments?.getString("line")

            refreshText(targetStation)
            refreshLogo(line)
            refreshTime()
            refreshGPS()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // targetStation에 따라 text 변경
    private fun refreshText(targetStation: String?) {
        val text: String = "역까지 N역 남음" // 함수로 거리 출력해야 함
        val distToDest: String = targetStation + text
        binding.distToDest.text = distToDest
    }

    // line 값에 따라 logo image 변경
    private fun refreshLogo(line: String?) {
        when (line){
            "1호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_1)
            "2호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_2)
            "3호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_3)
            "4호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_4)
            "5호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_5)
            "6호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_6)
            "7호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_7)
            "8호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_8)
            "9호선" -> binding.logoImage.setImageResource(R.drawable.ic_line_9)
            else -> binding.logoImage.setImageResource(R.drawable.ic_line_1)
        }
    }

    // 현재 시각 출력
    private fun refreshTime() {
        val time: Long = Date().time
        val dateFormat = SimpleDateFormat("aa h:mm:ss", Locale("ko", "KR"))
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val curTime = dateFormat.format(time).toString() + " 기준"
        binding.refreshTime.text = curTime
    }


    // GPS 포그라운드 권한 있는지 체크
    // 없으면 권한 요청, 있으면 현재 위치 출력
    // 보완 필요한 듯
    @RequiresApi(Build.VERSION_CODES.N)
    private fun refreshGPS() {
        // check whether permission is already there
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // request permission to access location information
            Log.d("tag", "request permission")
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        // Precise location access granted.
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        // Only approximate location access granted.
                    } else -> {
                    // No location access granted.
                }
                }
            }

            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION))

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        // if permission is already there
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            Log.d("Tag", "location: $location")
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                val textGPS = "$latitude, $longitude"
                binding.gpsNow.text = textGPS
                Log.d("Tag", "Latitude: $latitude, Longitude: $longitude")
            }
        }


    }
}