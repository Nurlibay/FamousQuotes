package com.example.famousquotes.favorite

import com.example.famousquotes.data.entities.CitataWithAuthor

interface FavoriteView {
    fun setData(models: List<CitataWithAuthor>)
}