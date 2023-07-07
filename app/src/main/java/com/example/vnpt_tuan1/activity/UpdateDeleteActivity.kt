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
import com.example.vnpt_tuan1.databinding.ActivityUpdateDeleteBinding
import com.example.vnpt_tuan1.model.Book
import java.util.Calendar

class UpdateDeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDeleteBinding
    private lateinit var book : Book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hiện ra thông tin của book cần update
        val intent = intent
        book = (intent.getSerializableExtra("book") as Book?)!!

        binding.edName.setText(book.name)
        binding.edPublishDate.setText(book.releaseDate)
        if(book.language == "java") binding.radioButtonJava.isChecked = true
        else binding.radioButtonKotlin.isChecked = true
        binding.spinnerPublisher.setAdapter(
            ArrayAdapter<String>(
                this, R.layout.item_spinner,
                resources.getStringArray(R.array.nxb)
            )
        )
        for (i in 0 until binding.spinnerPublisher.getCount()) {
            if (book.publisher.equals(binding.spinnerPublisher.getItemAtPosition(i))) {
                binding.spinnerPublisher.setSelection(i)
                break
            }
        }

        binding.edPublishDate.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(this@UpdateDeleteActivity,
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
                val ngonngu = if(binding.radioButtonJava.isChecked) "java"
                else "kotlin"
                val publishDate: String = binding.edPublishDate.getText().toString()
                val publisher: String = binding.spinnerPublisher.getSelectedItem().toString()
                val bookToUpdate = Book(id= book.id ,name = name, language = ngonngu, releaseDate = publishDate, publisher= publisher)
                val db = AppDatabase.getInstance(this)
                val bookDAO = db?.BookDAO()!!
                Log.e("HUYCD" , bookToUpdate.toString())
                bookDAO.update(bookToUpdate)
                val intent = Intent(this@UpdateDeleteActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnDelete.setOnClickListener{
            val db = AppDatabase.getInstance(this)
            val bookDAO = db?.BookDAO()!!
            bookDAO.delete(book)
            Toast.makeText(this, "Đã xóa sách", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnCancel.setOnClickListener{
            finish()
        }
    }
}