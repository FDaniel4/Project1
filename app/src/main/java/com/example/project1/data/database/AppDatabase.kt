package com.example.project1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project1.data.dao.ServiceDao
import com.example.project1.data.model.ServiceEntity
import com.example.project1.data.model.ServiceModel


@Database(entities = [ServiceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}