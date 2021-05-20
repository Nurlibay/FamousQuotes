package uz.texnopos.famousquotes.favorite

import uz.texnopos.famousquotes.data.entities.Citata

class FavoritePresenter(private val dao: uz.texnopos.famousquotes.data.dao.CitataDao, private val view: FavoriteView) {
    fun getFavorites(){
        view.setData(dao.getFavorites())
    }
    fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }
}