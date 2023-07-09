package com.example.vnpt_tuan1.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.databinding.ActivityAutoCompleteBinding


class AutoCompleteActivity : AppCompatActivity() {
    private lateinit var dataSource: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var binding : ActivityAutoCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutoCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSource = mutableListOf("Apple", "Banana", "Cherry", "Durian", "Elderberry")

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dataSource)
        binding.autoCompleteTextView.setAdapter(adapter)

        binding.autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val filteredData = dataSource.filter { it.startsWith(s.toString(), ignoreCase = true) }
                adapter.clear()
                adapter.addAll(filteredData)
                adapter.notifyDataSetChanged()
            }
        })
        binding.updateButton.setOnClickListener {
            val newData = binding.editText.text.toString()
            if (newData.isNotBlank()) {
                dataSource.add(newData)
                adapter.add(newData)
                adapter.notifyDataSetChanged()
                binding.editText.text.clear()
            }
        }
    }
}
