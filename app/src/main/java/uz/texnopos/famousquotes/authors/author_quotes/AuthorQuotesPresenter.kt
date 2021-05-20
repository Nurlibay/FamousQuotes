package uz.texnopos.famousquotes.authors.author_quotes

import uz.texnopos.famousquotes.data.entities.Citata

class AuthorQuotesPresenter(private val dao: uz.texnopos.famousquotes.data.dao.CitataDao, private val view: AuthorQuotesView) {
    fun getCitataByAuthorId(authorId: Int){
        view.setData(dao.getCitataByAuthorId(authorId))
    }
    fun searchCitataByAuthor(authorId: Int, word: String){
        view.searchCitataByAuthor(dao.searchCitataByAuthor(authorId, word))
    }
    fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }
}