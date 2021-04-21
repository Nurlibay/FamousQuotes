package com.example.famousquotes._room_dabase.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
data class Themes(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="theme_name") val theme_name: String
)