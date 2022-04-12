package com.example.networkingsample.data.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.networkingsample.R
import com.example.networkingsample.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var bindingPage: FragmentSecondBinding? = null
    private val binding get() = bindingPage!!
    private val arguments: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingPage = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments.cars.id
        val name = arguments.cars.name
        val price = arguments.cars.price

        binding.tvId.text = id.toString()
        binding.tvCar.text = name
        binding.tvPrice.text = price.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingPage = null
    }

}