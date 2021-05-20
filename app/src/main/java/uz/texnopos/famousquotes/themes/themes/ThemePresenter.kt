package uz.texnopos.famousquotes.themes.themes

class ThemePresenter(private val dao: uz.texnopos.famousquotes.data.dao.CitataDao, private val view: ThemeView) {
    fun getAllThemes(){
        view.setData(dao.getAllTheme())
    }
}