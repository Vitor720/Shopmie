package com.example.shopmie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopmie.databinding.OrderItemTableRowBinding
import com.example.shopmie.domain.model.OrderItemUI

class SalesAdapter() : RecyclerView.Adapter<SalesAdapter.SalesViewHolder>() {

    val items = mutableListOf<OrderItemUI?>(null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val viewHolder = OrderItemTableRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SalesViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val item = items[position] ?: return

        holder.bind(item)
    }

    fun addItem(item: OrderItemUI) {
        items.add(item)
        notifyItemChanged(items.size - 1)
    }

    inner class SalesViewHolder(
        val binding: OrderItemTableRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrderItemUI) {
            binding.tvProductName.text = item.name
            binding.tvProductQty.text = item.quantity.toString()
            binding.tvProductPrice.text = item.price.toString()
            binding.tvProductTotal.text = item.totalItemAmount.toString()

        }
    }
}