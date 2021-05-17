package com.example.famousquotes.authors.author

import com.example.famousquotes.data.entities.Author

interface AuthorView {
    fun setData(models: List<Author>)
    fun searchAuthorByName(models: List<Author>)
}