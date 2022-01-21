package com.example.subway

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.subway.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {

    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!
    var isCheckedSwitch1: Boolean = false
    var isCheckedSwitch2: Boolean = false


    override fun onResume() {
        super.onResume()


            binding.toggleSwitch1.setOnClickListener {
                // binding.toggleSwitch1.playAnimation()
                if (!isCheckedSwitch1) {
                    isCheckedSwitch1 = true
                    val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(500)
                    animator.addUpdateListener {
                        binding.toggleSwitch1.setProgress(animator.animatedValue as Float)
                    }
                    animator.start()
                } else if (isCheckedSwitch1) {
                    isCheckedSwitch1 = false
                    val animator = ValueAnimator.ofFloat(0.5f, 1f).setDuration(500)
                    animator.addUpdateListener {
                        binding.toggleSwitch1.setProgress(animator.animatedValue as Float)
                    }
                    animator.start()
                }
            }


        binding.toggleSwitch2.setOnClickListener {
            // binding.toggleSwitch1.playAnimation()
            if (!isCheckedSwitch2) {
                isCheckedSwitch2 = true
                val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(500)
                animator.addUpdateListener {
                    binding.toggleSwitch2.setProgress(animator.animatedValue as Float)
                }
                animator.start()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}