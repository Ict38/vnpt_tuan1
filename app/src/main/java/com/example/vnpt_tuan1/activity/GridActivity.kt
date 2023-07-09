package com.example.vnpt_tuan1.activity

import Cat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.adapter.CatAdapter
import com.example.vnpt_tuan1.databinding.ActivityGridBinding


class GridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridBinding
    private lateinit var adapter: CatAdapter
    private lateinit var cats : ArrayList<Cat>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cats = ArrayList<Cat>()

        cats.add(Cat(R.drawable.cat1, "meo 1"))
        cats.add(Cat(R.drawable.cat1, "meo 2"))
        cats.add(Cat(R.drawable.cat1, "meo 3"))
        cats.add(Cat(R.drawable.cat1, "meo 4"))
        cats.add(Cat(R.drawable.cat1, "meo 5"))
        cats.add(Cat(R.drawable.cat1, "meo 6"))
        adapter = CatAdapter(cats, this)
        val manager = GridLayoutManager(this, 3)
        binding.rview.setLayoutManager(manager)
        binding.rview.setAdapter(adapter)

        binding.addButton.setOnClickListener{
            adapter!!.addCat(cats.get(0))
        }
    }

}