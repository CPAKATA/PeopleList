package com.example.listofpeople.preview.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.listofpeople.data.User
import com.example.listofpeople.data.UserDatabase
import com.example.listofpeople.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeopleInfoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PeopleRepository

    init {
        val userDAO = UserDatabase.getDatabase(application).userDao()
        repository = PeopleRepository(userDAO)
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PeopleInfoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PeopleInfoViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}