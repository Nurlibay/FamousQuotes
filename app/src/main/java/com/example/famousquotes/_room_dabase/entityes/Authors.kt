package com.example.famousquotes._room_dabase.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class Authors(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="author_name") val author_name: String
)