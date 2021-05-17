package com.example.famousquotes.favorite

import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.entities.Citata

class FavoritePresenter(private val dao: CitataDao, private val view: FavoriteView) {
    fun getFavorites(){
        view.setData(dao.getFavorites())
    }
    fun setFavorite(citata: Citata){
        dao.citataUpdate(citata)
    }
}