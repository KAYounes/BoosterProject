package com.example.newsappinkotlin.Database
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<DataModel>>
    private val repository: Repository
    init {
        val newsDao = DataBase.getDatabase(application).newsDao()
        repository = Repository(newsDao)
        readAllData = repository.readAllData

    }
    fun saveNews(dataModel : DataModel){
     repository.addNews(dataModel)
    }


    
}