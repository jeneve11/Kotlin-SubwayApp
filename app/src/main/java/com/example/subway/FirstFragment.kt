package com.example.subway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.subway.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        // onResume 이 함수는 앱이 비활성 -> 활성 전환 될때마다 실행됨
        // ex) 앱 쓰다가 잠시 카톡와서 보고 다시 이 앱 켜면 실행됨
        // 앞에 기본 세팅을 여기 말고 onCreateView 이런곳으로 넘겨야 할 것 같은데?
        super.onResume()

        // 이거 여기 있으면 onResume 실행될때마다 dropdown menu가 default로 바뀌는 문제 있음 - 수정 필요
        // set default dropdown menu
        val itemSubwayLine = resources.getStringArray(R.array.subwayLines)
        val arrayAdapterSubwayLine = ArrayAdapter(requireContext(), R.layout.subwayline_item, itemSubwayLine)
        binding.autoCompleteTextView.setAdapter(arrayAdapterSubwayLine)

        var itemSubwayStation = resources.getStringArray(R.array.line_total)
        var arrayAdapterSubwayStation = ArrayAdapter(requireContext(), R.layout.subwayline_item, itemSubwayStation)
        binding.autoCompleteTextView2.setAdapter(arrayAdapterSubwayStation)

        // subwayLine 선택에 따라 subwayStation dropdown menu 변경
        // 반대(subwayStation 선택에 따라 subwayLine 변경) 기능 추가 필요
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
            itemSubwayStation = resources.getStringArray(line)
            arrayAdapterSubwayStation = ArrayAdapter(requireContext(), R.layout.subwayline_item, itemSubwayStation)
            binding.autoCompleteTextView2.setAdapter(arrayAdapterSubwayStation)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}