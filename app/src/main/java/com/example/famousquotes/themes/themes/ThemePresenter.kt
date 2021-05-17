package com.example.famousquotes.themes.themes

import com.example.famousquotes.data.dao.CitataDao

class ThemePresenter(private val dao: CitataDao, private val view: ThemeView) {
    fun getAllThemes(){
        view.setData(dao.getAllTheme())
    }
}