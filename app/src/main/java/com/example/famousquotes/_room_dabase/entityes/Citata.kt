package com.example.famousquotes._room_dabase.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citata")
data class Citata(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="citata_text") val citata_text: String,
    @ColumnInfo(name="theme_id") val theme_id: Int,
    @ColumnInfo(name="author_id") val author_id: Int
)