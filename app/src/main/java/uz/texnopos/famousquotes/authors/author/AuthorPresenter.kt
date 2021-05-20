package uz.texnopos.famousquotes.authors.author

class AuthorPresenter(private val dao: uz.texnopos.famousquotes.data.dao.CitataDao, private val view: AuthorView) {
    fun getAllAuthors(){
        view.setData(dao.getAllAuthors())
    }
    fun searchAuthorByName(word: String){
        view.searchAuthorByName(dao.searchAuthorByName(word))
    }
}