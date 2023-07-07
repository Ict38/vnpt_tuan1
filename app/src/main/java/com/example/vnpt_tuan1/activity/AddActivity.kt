package com.example.vnpt_tuan1.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.data.AppDatabase
import com.example.vnpt_tuan1.databinding.ActivityAddBinding
import com.example.vnpt_tuan1.model.Book
import java.util.Calendar

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerPublisher.setAdapter(
            ArrayAdapter<String>(
                this, R.layout.item_spinner,
                resources.getStringArray(R.array.nxb)
            )
        )

        binding.edPublishDate.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(this@AddActivity,
                { datePicker, y, m, d ->
                    var m = m
                    m++
                    val date = "$d/$m/$y"
                    binding.edPublishDate.setText(date)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        binding.btnUpdate.setOnClickListener{
            Log.e("Check kkkkk", binding.edName.text.toString())
            if(binding.edName.text.toString() == "" || binding.edPublishDate.text.toString() == ""){
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
            else{
                val name: String = binding.edName.getText().toString()
                var ngonngu = if(binding.radioButtonJava.isChecked) "java"
                else "kotlin"
                val publishDate: String = binding.edPublishDate.getText().toString()
                val publisher: String = binding.spinnerPublisher.getSelectedItem().toString()
                val bookToAdd = Book(name = name, language = ngonngu, releaseDate = publishDate, publisher= publisher)
                val db = AppDatabase.getInstance(this)
                val bookDAO = db?.BookDAO()!!
                bookDAO.insertAll(bookToAdd)
                val intent = Intent(this@AddActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnCancel.setOnClickListener{
            finish()
        }
    }
}