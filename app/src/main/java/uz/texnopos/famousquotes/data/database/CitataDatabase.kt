package uz.texnopos.famousquotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.texnopos.famousquotes.data.dao.CitataDao
import uz.texnopos.famousquotes.data.entities.Author
import uz.texnopos.famousquotes.data.entities.Citata
import uz.texnopos.famousquotes.data.entities.Theme

@Database(entities = [Theme::class, Author::class, Citata::class], version = 1)
abstract class CitataDatabase: RoomDatabase() {

    companion object {
        lateinit var INSTANCE: CitataDatabase
        fun getInstance(context: Context) :CitataDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context, CitataDatabase::class.java,
                    "FamousPeopleQuotes.db")
                    .createFromAsset("FamousPeopleQuotes.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): CitataDao
}