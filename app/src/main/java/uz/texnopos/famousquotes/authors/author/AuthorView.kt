package uz.texnopos.famousquotes.authors.author

import uz.texnopos.famousquotes.data.entities.Author

interface AuthorView {
    fun setData(models: List<Author>)
    fun searchAuthorByName(models: List<Author>)
}