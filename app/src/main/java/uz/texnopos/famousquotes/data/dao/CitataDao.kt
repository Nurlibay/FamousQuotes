package uz.texnopos.famousquotes.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import uz.texnopos.famousquotes.data.entities.*

@Dao
interface CitataDao {
    @Query("SELECT * FROM theme")
    fun getAllTheme(): List<Theme>

    @Query("SELECT * FROM citata WHERE id=:id")
    fun getCitataById(id: Int): Citata

    @Query("SELECT * FROM authors")
    fun getAllAuthors(): List<Author>

    @Query("SELECT * FROM citata")
    fun getAllCitata(): List<Citata>

    @Query("SELECT * FROM citata WHERE theme_id=:themeId")
    fun getCitataByThemeId(themeId: Int): List<Citata>

    @Query("SELECT * FROM citata WHERE author_id=:authorId")
    fun getCitataByAuthorId(authorId: Int): List<CitataWithAuthor>


    @Transaction
    @Query("SELECT * FROM citata WHERE theme_id=:themeId")
    fun getCitataWithAuthorByThemeId(themeId: Int): List<CitataWithAuthor>

    // ThemeQuotesFragment Search Query and Function here ...
    @Query("SELECT * FROM citata WHERE theme_id=:themeId and citata_text LIKE :word")
    fun searchCitataByText(themeId: Int, word: String): List<CitataWithAuthor>

    // AuthorsQuotesFragment Search Query and Function here ...
    @Query("SELECT * FROM citata WHERE author_id=:authorId and citata_text LIKE :word")
    fun searchCitataByAuthor(authorId: Int, word: String): List<CitataWithAuthor>

    // AuthorsFragment Search Query and Function here ...
    @Query("SELECT * FROM authors WHERE author_name LIKE :word")
    fun searchAuthorByName(word: String): List<Author>

    @Update
    fun citataUpdate(citata: Citata)

    @Query("SELECT * FROM citata WHERE id=:id")
    fun citataById(id: Int): Citata

    @Query("SELECT * FROM citata WHERE isFavorite=1")
    fun getFavorites(): List<CitataWithAuthor>
}