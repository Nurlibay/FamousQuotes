package com.example.famousquotes._room_dabase.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.famousquotes._room_dabase.entityes.Authors
import com.example.famousquotes._room_dabase.entityes.Citata
import com.example.famousquotes._room_dabase.entityes.Themes

@Dao
interface CitataDao {
    @Query("SELECT * FROM theme")
    fun getAllTheme(): List<Themes>

    @Query("SELECT * FROM authors")
    fun getAllTAuthors(): List<Authors>

    @Query("SELECT * FROM citata")
    fun getAllCitata(): List<Citata>
}