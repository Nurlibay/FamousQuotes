package com.example.famousquotes.authors.author_quotes

import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.entities.Citata

class AuthorQuotesPresenter(private val dao: CitataDao, private val view: AuthorQuotesView) {
    fun getCitataByAuthorId(authorId: Int){
        view.setData(dao.getCitataByAuthorId(authorId))
    }
    fun searchCitataByAuthor(authorId: Int, word: String){
        view.searchCitataByAuthor(dao.searchCitataByAuthor(authorId, word))
    }
    fun setFavorite(citata: Citata){
        dao.citataUpdate(citata)
    }
}