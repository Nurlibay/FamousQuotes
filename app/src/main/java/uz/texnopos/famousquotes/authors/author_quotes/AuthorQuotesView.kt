package uz.texnopos.famousquotes.authors.author_quotes

import uz.texnopos.famousquotes.data.entities.CitataWithAuthor

interface AuthorQuotesView {
    fun setData(models: List<CitataWithAuthor>)
    fun searchCitataByAuthor(models: List<CitataWithAuthor>)
}