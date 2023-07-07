package com.example.vnpt_tuan1.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.activity.UpdateDeleteActivity
import com.example.vnpt_tuan1.adapter.RecyclerViewAdapter
import com.example.vnpt_tuan1.data.AppDatabase
import com.example.vnpt_tuan1.data.BookDAO
import com.example.vnpt_tuan1.databinding.FragmentSearchBinding
import com.example.vnpt_tuan1.model.Book
import java.util.Locale

class FragmentSearch : Fragment(), RecyclerViewAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var bookDAO: BookDAO
    private lateinit var bookList : ArrayList<Book>
    private lateinit var tempBookList : ArrayList<Book>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.let { AppDatabase.getInstance(it) }
        bookDAO = db?.BookDAO()!!
        tempBookList = bookDAO.getAll() as ArrayList<Book>
        bookList = bookDAO.getAll() as ArrayList<Book>
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                tempBookList.clear()
                val searchText = query!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    bookList.forEach {
                        if(it.name?.lowercase(Locale.getDefault())?.contains(searchText) == true){
                            tempBookList.add(it)
                        }
                    }
                    tempBookList.forEach {
                        Log.e("book temp", it.toString())
                    }
                    adapter.notifyDataSetChanged()
                }
                else{
                    tempBookList.addAll(bookList)
                    adapter.notifyDataSetChanged()
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                tempBookList.clear()
                Log.e("BOOK1", tempBookList.toString())
                Log.e("BOOK2", bookList.toString())
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    bookList.forEach {
                        if(it.name?.lowercase(Locale.getDefault())?.contains(searchText) == true){
                            tempBookList.add(it)
                        }
                    }
                    tempBookList.forEach {
                        Log.e("book temp", it.toString())
                    }
                    adapter.notifyDataSetChanged()
                }
                else{
                    tempBookList.addAll(bookList)
                    adapter.notifyDataSetChanged()
                }
                return false
            }
        })
        adapter = RecyclerViewAdapter(tempBookList, this)
        adapter.setBookList(tempBookList)
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rview.layoutManager = manager
        binding.rview.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val book: Book = adapter.getItem(position)
        val intent = Intent(activity, UpdateDeleteActivity::class.java)
        intent.putExtra("book", book)
        startActivity(intent)
    }
}