package com.example.todolist.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("title")
    val title:String,
    @ColumnInfo("description")
    val description:String,
)
