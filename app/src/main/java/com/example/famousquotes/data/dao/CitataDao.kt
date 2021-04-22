package com.example.famousquotes.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.famousquotes.data.entityes.Author
import com.example.famousquotes.data.entityes.Citata
import com.example.famousquotes.data.entityes.Theme

@Dao
interface CitataDao {
    @Query("SELECT * FROM theme")
    fun getAllTheme(): List<Theme>

    @Query("SELECT * FROM authors")
    fun getAllTAuthors(): List<Author>

    @Query("SELECT * FROM citata")
    fun getAllCitata(): List<Citata>

    @Query("SELECT * FROM citata WHERE theme_id=:themeId")
    fun getCitataByThemeId(themeId: Int): List<Citata>
}