package com.example.vnpt_tuan1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name : String,
    val language : String,
    val releaseDate : String,
    val publisher : String
) : Serializable{
}