package uz.texnopos.famousquotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.famousquotes.data.entities.Author
import uz.texnopos.famousquotes.data.entities.Citata
import uz.texnopos.famousquotes.data.entities.Theme

@Database(entities = [Theme::class, Author::class, Citata::class], version = 1)
abstract class CitataDatabase: RoomDatabase() {

    companion object {
        lateinit var INSTANCE: uz.texnopos.famousquotes.data.database.CitataDatabase
        fun getInstance(context: Context) : uz.texnopos.famousquotes.data.database.CitataDatabase {
            if (!uz.texnopos.famousquotes.data.database.CitataDatabase.Companion::INSTANCE.isInitialized) {
                uz.texnopos.famousquotes.data.database.CitataDatabase.Companion.INSTANCE = Room.databaseBuilder(
                    context, uz.texnopos.famousquotes.data.database.CitataDatabase::class.java,
                    "FamousPeopleQuotes.db")
                    .createFromAsset("FamousPeopleQuotes.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return uz.texnopos.famousquotes.data.database.CitataDatabase.Companion.INSTANCE
        }
    }

    abstract fun dao(): uz.texnopos.famousquotes.data.dao.CitataDao
}