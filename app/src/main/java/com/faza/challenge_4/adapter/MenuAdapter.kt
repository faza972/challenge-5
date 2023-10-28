package com.faza.challenge_4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faza.challenge_4.R
import com.faza.challenge_4.model.Menu

class MenuAdapter(private val menu: ArrayList<Menu>, var isGrid: Boolean = true, var onItemClick: ((Menu)-> Unit) ? = null) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.tv_namaMenu)!!
        val image: ImageView = itemView.findViewById(R.id.iv_gambar)!!
        val price: TextView = itemView.findViewById(R.id.tv_harga)!!
        val notes: TextView = itemView.findViewById(R.id.tv_notes)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutRestId = if (isGrid) R.layout.item_menu_grid else R.layout.item_menu_linear
        val view: View = LayoutInflater.from(parent.context).inflate(layoutRestId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val (name, image, price, notes) = menu[position]
        holder.image.setImageResource(image)
        holder.name.text = name
        holder.price.text = price.toString()
        holder.notes.text = notes

        val currentItem = menu[position]
        holder.itemView.setOnClickListener {
            onItemClick?. invoke(currentItem)
        }
    }
    override fun getItemCount(): Int {
        return menu.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun reloadData(newData: ArrayList<Menu>){
        menu.clear()
        menu.addAll(newData)
        notifyDataSetChanged()
    }
}


