package com.example.famousquotes.themes.theme_quotes
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.entities.Citata

class ThemeQuotesPresenter(private val dao: CitataDao, private val view: ThemeQuotesView) {
    fun getCitataWithAuthorByThemeId(themeId: Int){
        view.setData(dao.getCitataWithAuthorByThemeId(themeId))
    }
    fun searchCitataByText(themeId: Int, searchWord: String, words: List<String>) {
        view.setSearchResult(words, dao.searchCitataByText(themeId, searchWord))
    }
    fun setFavorite(citata: Citata){
        dao.citataUpdate(citata)
    }
}