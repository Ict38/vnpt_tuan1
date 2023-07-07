package com.example.vnpt_tuan1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vnpt_tuan1.model.Book

@Dao
interface BookDAO {
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>
    @Insert
    fun insertAll(vararg book: Book)

    @Update
    fun update(book  : Book)
    @Delete
    fun delete(book: Book)
}

