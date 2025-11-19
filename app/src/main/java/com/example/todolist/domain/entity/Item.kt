package com.example.todolist.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("color")
    val color: Int,
)
