package com.example.famousquotes.themes.themes

import com.example.famousquotes.data.entities.Theme

interface ThemeView {
    fun setData(models: List<Theme>)
}