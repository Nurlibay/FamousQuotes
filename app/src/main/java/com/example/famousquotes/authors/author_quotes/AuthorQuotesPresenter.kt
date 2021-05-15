package com.example.famousquotes.authors.author_quotes

import com.example.famousquotes.data.dao.CitataDao

class AuthorQuotesPresenter(private val dao: CitataDao, private val view: AuthorQuotesView) {
    fun getCitataByAuthorId(authorId: Int){
        view.setData(dao.getCitataByAuthorId(authorId))
    }
}