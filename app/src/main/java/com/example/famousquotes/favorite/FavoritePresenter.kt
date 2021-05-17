package com.example.famousquotes.favorite

import com.example.famousquotes.data.dao.CitataDao

class FavoritePresenter(private val dao: CitataDao, private val view: FavoriteView) {
    fun getFavorites(){
        view.setData(dao.getFavorites())
    }
}