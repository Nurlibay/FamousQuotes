package com.example.famousquotes.authors.author_quotes

import com.example.famousquotes.data.entities.CitataWithAuthor

interface AuthorQuotesView {
    fun setData(models: List<CitataWithAuthor>)
    fun searchCitataByAuthor(models: List<CitataWithAuthor>)
}