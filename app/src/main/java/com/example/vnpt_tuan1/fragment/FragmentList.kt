package com.example.vnpt_tuan1.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vnpt_tuan1.activity.UpdateDeleteActivity
import com.example.vnpt_tuan1.adapter.RecyclerViewAdapter
import com.example.vnpt_tuan1.data.AppDatabase
import com.example.vnpt_tuan1.data.BookDAO
import com.example.vnpt_tuan1.databinding.FragmentListBinding
import com.example.vnpt_tuan1.model.Book


class FragmentList : Fragment(), RecyclerViewAdapter.OnItemClickListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var bookDAO: BookDAO
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.let { AppDatabase.getInstance(it) }
        bookDAO = db?.BookDAO()!!
        var bookList = bookDAO.getAll()
        if(bookList.isEmpty()){
            val book1 = Book(0, "The Great Gatsby", "English", "1925", "Charles Scribner's Sons")
            val book2 = Book(0, "To Kill a Mockingbird", "English", "1960", "J. B. Lippincott & Co.")
            val book3 = Book(0, "1984", "English", "1949", "Secker & Warburg")

            bookDAO.insertAll(book1,book2, book3)
            bookList = bookDAO.getAll()
        }
        adapter = RecyclerViewAdapter(bookList, this)
        adapter.setBookList(bookList)
        val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        val list: List<Book> = bookDAO.getAll()
        adapter = RecyclerViewAdapter(list,this)
        adapter.setBookList(list)
    }

    override fun onItemClick(position: Int) {
        val book: Book = adapter.getItem(position)
        val intent = Intent(activity, UpdateDeleteActivity::class.java)
        intent.putExtra("book", book)
        startActivity(intent)
    }


}