package com.example.newsappinkotlin.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
@Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSavedNews(DataModel: DataModel)
    @Query("Select * From newsdata ORDER BY id ASC")
    fun readAllData(): LiveData<List<DataModel>>

}