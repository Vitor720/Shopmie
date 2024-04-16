package com.example.shopmie.ui.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopmie.R
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
        val item = items[position]

        if (item == null) return holder.bindHeader()

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
            binding.tvProductName.apply {
                text = item.name
                setTypeface(null, Typeface.NORMAL)
            }
            binding.tvProductQty.apply {
                text = item.quantity.toString()
                setTypeface(null, Typeface.NORMAL)
            }
            binding.tvProductPrice.apply {
                text = item.price.toString()
                setTypeface(null, Typeface.NORMAL)
            }
            binding.tvProductTotal.apply {
                text = item.totalItemAmount.toString()
                setTypeface(null, Typeface.NORMAL)
            }
        }

        fun bindHeader() {
            binding.apply {
                tvProductName.apply {
                    text = root.context.getString(R.string.name)
                    setTypeface(null, Typeface.BOLD)
                }
                tvProductTotal.apply {
                    text = root.context.getString(R.string.total_value)
                    setTypeface(null, Typeface.BOLD)
                }
                tvProductPrice.apply {
                    text = root.context.getString(R.string.value)
                    setTypeface(null, Typeface.BOLD)
                }
                tvProductQty.apply {
                    text = root.context.getString(R.string.quantity)
                    setTypeface(null, Typeface.BOLD)
                }
            }
        }
    }
}