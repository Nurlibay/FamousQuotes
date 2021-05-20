package uz.texnopos.famousquotes.themes.theme_quotes
import uz.texnopos.famousquotes.data.entities.Citata

class ThemeQuotesPresenter(private val dao: uz.texnopos.famousquotes.data.dao.CitataDao, private val view: ThemeQuotesView) {
    fun getCitataWithAuthorByThemeId(themeId: Int){
        view.setData(dao.getCitataWithAuthorByThemeId(themeId))
    }
    fun searchCitataByText(themeId: Int, searchWord: String, words: List<String>) {
        view.setSearchResult(words, dao.searchCitataByText(themeId, searchWord))
    }
    fun setFavorite(citata: Citata){
        citata.isFavorite = 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }
}