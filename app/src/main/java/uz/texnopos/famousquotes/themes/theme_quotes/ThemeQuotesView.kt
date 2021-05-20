package uz.texnopos.famousquotes.themes.theme_quotes

import uz.texnopos.famousquotes.data.entities.CitataWithAuthor

interface ThemeQuotesView {
    fun setData(models: List<CitataWithAuthor>)
    fun setSearchResult(words: List<String>, result: List<CitataWithAuthor>)
}