package com.example.famousquotes.authors.author

import com.example.famousquotes.data.dao.CitataDao

class AuthorPresenter(private val dao: CitataDao, private val view: AuthorView) {
    fun getAllAuthors(){
        view.setData(dao.getAllAuthors())
    }
}