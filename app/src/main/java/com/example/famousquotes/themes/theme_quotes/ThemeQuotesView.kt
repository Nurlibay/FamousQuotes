package com.example.famousquotes.themes.theme_quotes

import com.example.famousquotes.data.entities.CitataWithAuthor

interface ThemeQuotesView {
    fun setData(models: List<CitataWithAuthor>)
    fun setSearchResult(words: List<String>, result: List<CitataWithAuthor>)
}