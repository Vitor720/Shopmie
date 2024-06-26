package com.example.shopmie.ui.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopmie.R
import com.example.shopmie.databinding.FragmentSalesBinding
import com.example.shopmie.ui.adapters.SalesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SalesFragment : Fragment() {

    private val viewModel: SalesViewModel by viewModel()
    private var binding: FragmentSalesBinding? = null
    private val adapter = SalesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getOrderID()
        setUpObservers()
        setUpListeners()
        setUpToolbar()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        configureView()
    }

    private fun configureView() {
        binding?.etClientInput?.editText?.setText(viewModel.client.orEmpty())
    }

    private fun setUpToolbar() {
        binding?.tbSales?.title = getString(R.string.make_order)

        binding?.tbSales?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setUpListeners() {

        binding?.btnInclude?.setOnClickListener {
            val productName = binding?.etProductInput?.editText?.text.toString()
            val quantity = binding?.etQuantityInput?.editText?.text.toString().toIntOrNull()
            val price = binding?.etUnitValueInput?.editText?.text.toString().toDoubleOrNull()
            viewModel.addProduct(productName, quantity, price)

            clearProductFields()
        }

        binding?.btnSaveOrder?.setOnClickListener {
            viewModel.postSale()
            findNavController().popBackStack()
        }

        binding?.etProductInput?.editText?.addTextChangedListener {
            verifyProductFieldToEnableInclude()
        }

        binding?.etQuantityInput?.editText?.addTextChangedListener {
            val quantity = it.toString().toIntOrNull()
            val price = binding?.etUnitValueInput?.editText?.text.toString().toDoubleOrNull()
            val total: Double = quantity?.times(price ?: 0.0) ?: 0.0

            binding?.tvTotalItemValue?.text = context?.getString(
                R.string.total_item_value,
                total.toString()
            )
            verifyProductFieldToEnableInclude()
        }

        binding?.etUnitValueInput?.editText?.addTextChangedListener {
            val quantity = binding?.etQuantityInput?.editText?.text.toString().toIntOrNull()
            val price = it.toString().toDoubleOrNull()
            val total: Double = quantity?.times(price ?: 0.0) ?: 0.0

            binding?.tvTotalItemValue?.text = context?.getString(
                R.string.total_item_value,
                total.toString()
            )

            verifyProductFieldToEnableInclude()
        }

        binding?.etClientInput?.editText?.addTextChangedListener {
            viewModel.client = it.toString()
        }

        binding?.btnCancel?.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun clearProductFields() {
        binding?.etProductInput?.editText?.setText("")
        binding?.etQuantityInput?.editText?.setText("")
        binding?.etUnitValueInput?.editText?.setText("")

        val inputManager = getSystemService<InputMethodManager>(requireContext(), InputMethodManager::class.java)
        inputManager?.hideSoftInputFromWindow(binding?.etUnitValueInput?.windowToken, 0)
    }

    private fun setUpObservers() {
        viewModel.orderID.observe(viewLifecycleOwner) {
            binding?.tvOrderId?.text = context?.getString(
                R.string.order_number, (it ?: 0).toString()
            )
        }

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.addItem(it.last())

            binding?.tvTotalItemsCount?.text = context?.getString(
                R.string.total_items_count,
                it.size.toString()
            )

            binding?.tvTotalOrderValue?.text = context?.getString(
                R.string.total_item_value,
                it.sumByDouble { it.price * it.quantity }.toString()
            )
        }

        viewModel.isOrderDataFilled.observe(viewLifecycleOwner) {
            binding?.btnSaveOrder?.isEnabled = it
        }
    }

    private fun verifyProductFieldToEnableInclude() {
        val isQuantityBiggerThanZero =
            (binding?.etQuantityInput?.editText?.text.toString().toIntOrNull() ?: 0) > 0

        binding?.btnInclude?.isEnabled = binding?.etProductInput?.editText?.text.toString().isNotEmpty()
                && binding?.etQuantityInput?.editText?.text.toString().isNotEmpty() && isQuantityBiggerThanZero
                && binding?.etUnitValueInput?.editText?.text.toString().isNotEmpty()
    }

    private fun setupRecyclerView() {
        binding?.rvProducts?.layoutManager = LinearLayoutManager(context)
        binding?.rvProducts?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}