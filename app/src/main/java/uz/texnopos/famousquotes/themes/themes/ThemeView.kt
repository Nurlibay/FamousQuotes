package uz.texnopos.famousquotes.themes.themes

import uz.texnopos.famousquotes.data.entities.Theme

interface ThemeView {
    fun setData(models: List<Theme>)
}