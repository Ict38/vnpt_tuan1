package com.example.vnpt_tuan1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vnpt_tuan1.model.Book

@Database(entities = arrayOf(Book::class), version = 1)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun BookDAO() : BookDAO
    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context): AppDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java
                , "book1.db").allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}