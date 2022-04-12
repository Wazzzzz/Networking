package com.example.networkingsample.data.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.networkingsample.data.adapter.MainAdapter
import com.example.networkingsample.data.model.GetAllCarResponseItem
import com.example.networkingsample.data.network.CarsApi
import com.example.networkingsample.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private var bindingPage: FragmentFirstBinding? = null
    private val binding get() = bindingPage!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingPage = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchAllData()
    }

    private fun fetchAllData() {
        CarsApi.instance.allCar().enqueue(object : Callback<List<GetAllCarResponseItem>> {
            override fun onResponse(
                call: Call<List<GetAllCarResponseItem>>,
                response: Response<List<GetAllCarResponseItem>>
            ) {
                val body = response.body()
                val code = response.code()
                if (code == 200) {
                    showList(body)
                    binding.progressBar.visibility = View.GONE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<GetAllCarResponseItem>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun showList(data: List<GetAllCarResponseItem>?) {

        val adapter = MainAdapter(object : MainAdapter.OnClickListener {
            override fun onCLickItem(data: GetAllCarResponseItem) {
            }
        })
        adapter.submitData(data)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingPage = null
    }

}