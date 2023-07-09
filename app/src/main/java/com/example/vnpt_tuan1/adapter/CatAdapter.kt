package com.example.vnpt_tuan1.adapter

import Cat
import com.example.vnpt_tuan1.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(mlist: MutableList<Cat>?, context: Context) :
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {
    private val mlist: MutableList<Cat>?
    private val context: Context

    init {
        this.mlist = mlist
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat: Cat = mlist!![position] ?: return
        holder.imageView.setImageResource(cat.img)
        holder.textView.setText(cat.name)
        holder.cardView.setOnClickListener {
            Toast.makeText(context, cat.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mlist?.size ?: 0
    }

    fun addCat(cat: Cat) {
        mlist!!.add(cat)
        notifyDataSetChanged()
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
         val textView: TextView
         val cardView: CardView

        init {
            imageView = itemView.findViewById<ImageView>(R.id.img)
            textView = itemView.findViewById<TextView>(R.id.name)
            cardView = itemView.findViewById<CardView>(R.id.cview)
        }
    }
}