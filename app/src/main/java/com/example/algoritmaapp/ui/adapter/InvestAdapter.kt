package com.example.algoritmaapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.algoritmaapp.R
import com.example.algoritmaapp.common.GenericDiffUtil
import com.example.algoritmaapp.data.model.ResponseResult
import com.example.algoritmaapp.databinding.ItemListBinding

class InvestAdapter: ListAdapter<ResponseResult, InvestViewHolder>(
    GenericDiffUtil<ResponseResult>(
        myItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        myContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
     class InvestViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ResponseResult) {
            with(binding){
                name.text=result.name
                vOne.text=result.vOne
                vTwo.text=result.vTwo
                vThree.text=result.vThree
                txtRate.text=result.rate
                vFour.text=result.vFour

            }

            val imageResource = if (result.image.equals("up", ignoreCase = true)) {
                R.drawable.ic_up

            } else {
                R.drawable.ic_down
            }

            binding.image.setImageResource(imageResource)

        }
    }




