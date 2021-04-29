package com.example.famousquotes.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.famousquotes.data.entities.*

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

    @Query("SELECT * FROM citata WHERE author_id=:authorId")
    fun getCitataByAuthorId(authorId: Int): List<Citata>

    @Transaction
    @Query("SELECT * FROM citata WHERE theme_id=:themeId")
    fun getCitataWithAuthorByThemeId(themeId: Int): List<CitataWithAuthor>

    @Update
    fun updateCitata(citata: Citata)
}