package uz.texnopos.famousquotes.favorite

import uz.texnopos.famousquotes.data.entities.CitataWithAuthor

interface FavoriteView {
    fun setData(models: List<CitataWithAuthor>)
}