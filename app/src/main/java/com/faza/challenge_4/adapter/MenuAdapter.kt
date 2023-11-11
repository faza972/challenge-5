package com.faza.challenge_4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faza.challenge_4.databinding.ItemMenuGridBinding
import com.faza.challenge_4.databinding.ItemMenuLinearBinding
import com.faza.challenge_4.model.ListData

class MenuAdapter(
    var isGrid: Boolean = true,
    private val onItemClick: OnClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differ = object : DiffUtil.ItemCallback<ListData>() {
        override fun areContentsTheSame(oldItem: ListData, newItem: ListData): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ListData, newItem: ListData): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val dif = AsyncListDiffer(this, differ)

    fun putListMenu(value: List<ListData?>?) = dif.submitList(value)
    inner class GridVH(private var binding: ItemMenuGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListData) {
            binding.apply {
                tvNameGrid.text = data.nama
                tvPriceGrid.text = data.hargaFormat
                Glide.with(this.ivImageGrid)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(ivImageGrid)
                }
            }
        }
    inner class ListVH(private var binding: ItemMenuLinearBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ListData) {
            binding.apply {
                tvMenuName.text = data.nama
                tvMenuPrice.text = data.hargaFormat
                Glide.with(this.ivMenuImage)
                    .load(data.imageUrl)
                    .fitCenter()
                    .into(ivMenuImage)
            }


        }
    }

    interface OnClickListener {
        fun onClick(listData: ListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return if (isGrid){
            GridVH(ItemMenuGridBinding.inflate(view, parent, false))
        } else {
            ListVH(ItemMenuLinearBinding.inflate(view,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dif.currentList[position]
        if (holder is GridVH) {
            holder.bind(data)
        } else if (holder is ListVH) {
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return dif.currentList.size
    }
}


