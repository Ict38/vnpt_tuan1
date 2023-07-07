package com.example.vnpt_tuan1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.model.Book

class RecyclerViewAdapter(
    private var bookList : List<Book>,
    private val listener : OnItemClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ListBookViewHolder>() {

    fun setBookList(bookList: List<Book>){
        this.bookList = bookList
    }

    fun getItem(position: Int): Book {
        return bookList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view,parent,false)
        return ListBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ListBookViewHolder, position: Int) {
        val book = bookList[position]
        holder.name.text = book.name
        holder.publishDate.text = book.releaseDate
        holder.publisher.text = book.publisher
        if(book.language == "java") {
            holder.btnJava.isChecked = true
            holder.btnKotlin.isChecked = false
        } else{
            holder.btnJava.isChecked = false
            holder.btnKotlin.isChecked = true
        }
    }

    inner class ListBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name : TextView
        val publishDate : TextView
        val publisher : TextView
        val btnJava : RadioButton
        val btnKotlin : RadioButton

        init {
            name = itemView.findViewById<TextView>(R.id.tvTen)
            btnJava = itemView.findViewById<RadioButton>(R.id.radio_button_java)
            btnKotlin = itemView.findViewById<RadioButton>(R.id.radio_button_kotlin)
            publishDate = itemView.findViewById<TextView>(R.id.tvNgayXB)
            publisher = itemView.findViewById<TextView>(R.id.tvNhaXB)
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            Log.d("position", "$position")
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}