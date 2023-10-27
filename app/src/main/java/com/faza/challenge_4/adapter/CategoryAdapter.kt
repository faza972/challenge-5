package com.faza.challenge_4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faza.challenge_4.databinding.ItemMenuGridBinding
import com.faza.challenge_4.model.CategoryData

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val differ = object : DiffUtil.ItemCallback<CategoryData>(){
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem == newItem
        }
    }

    private val diff = AsyncListDiffer(this, differ)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMenuGridBinding.inflate(view, parent, false))
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = diff.currentList[position]
        data.let { holder.bind(data) }
    }
    fun sendCategoryData(value: List<CategoryData?>?) = diff.submitList(value)
    inner class ViewHolder(private var binding: ItemMenuGridBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData){
            binding.apply {
                tvNameGrid.text = data.nama
                Glide.with(this.ivImageGrid)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(binding.ivImageGrid)
            }
        }
    }
}