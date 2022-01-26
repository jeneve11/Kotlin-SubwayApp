package com.example.subway

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.subway.databinding.FragmentTwoBinding
import com.github.barteksc.pdfviewer.util.FitPolicy

class FragmentTwo : Fragment() {
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)

        // 가능하면 호선별로 다른 pdf
        // 첫 포커싱을 확대해서 안되나
        binding.pdfViewer.fromAsset("map_korea.pdf")
            .pageFitPolicy(FitPolicy.HEIGHT) // mode to fit pages in the view
            //.nightMode(true) // toggle night mode
            .load()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}