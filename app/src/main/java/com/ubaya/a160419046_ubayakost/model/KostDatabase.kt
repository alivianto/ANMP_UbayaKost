package com.ubaya.a160419046_ubayakost.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.a160419046_ubayakost.util.MIGRATION_1_2

@Database(entities = [Kost::class, Comment::class, Bookmark::class, User::class], version = 2)
abstract class KostDatabase: RoomDatabase() {
    abstract fun kostDao(): KostDao
    abstract fun userDao():UserDao
    abstract fun bookmarkDao():BookmarkDao
    abstract fun commentDao():CommentDao

    companion object {
        @Volatile private var instance: RoomDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                KostDatabase::class.java,
                "kostdatabase"
            ).addMigrations(MIGRATION_1_2).build()

        operator fun invoke(context: Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}