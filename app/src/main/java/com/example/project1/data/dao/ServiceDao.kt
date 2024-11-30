package com.example.project1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.project1.data.model.ServiceEntity
import androidx.room.Query

@Dao
interface ServiceDao{
    @Query("SELECT * FROM ServiceEntity")
    fun getAll(): List<ServiceEntity>

    @Query("SELECT * FROM ServiceEntity WHERE id = :ServiceId")
    fun show(ServiceId: Int): ServiceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(services: List<ServiceEntity>)

    @Delete
    fun delete(service: ServiceEntity)
}