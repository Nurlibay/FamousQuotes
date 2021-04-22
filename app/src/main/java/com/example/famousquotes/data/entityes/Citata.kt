package com.example.famousquotes.data.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citata")
data class Citata(
        @PrimaryKey val id: Int,
        @ColumnInfo(name="citata_text") val text: String,
        @ColumnInfo(name="theme_id") val themeId: Int,
        @ColumnInfo(name="author_id") val authorId: Int
)