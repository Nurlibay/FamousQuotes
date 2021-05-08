package com.example.famousquotes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="author_name") var authorName: String
)