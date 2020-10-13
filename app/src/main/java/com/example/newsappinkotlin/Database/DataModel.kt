package com.example.newsappinkotlin.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsdata")
data class DataModel (@PrimaryKey(autoGenerate = true)
                      val id: Int
                      ,val text : String
                      ,val source : String)

//                    ,val image : String
//