package com.example.shopmie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shopmie.R
import com.example.shopmie.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTotalSales()

        setUpObservers()
        setUpToolBar()
        setUpListeners()

    }

    private fun setUpToolBar() {

    }

    private fun setUpObservers() {
        viewModel.salesAmount.observe(viewLifecycleOwner) {
            binding?.tvTotalSalesAmount?.text = it.toString()
        }
    }

    private fun setUpListeners() {
        binding?.btnRegisterSale?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_salesFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}