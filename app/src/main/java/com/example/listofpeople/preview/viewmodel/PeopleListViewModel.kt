package com.example.listofpeople.preview.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.listofpeople.data.User
import com.example.listofpeople.data.UserDatabase
import com.example.listofpeople.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PeopleListViewModel (application: Application): AndroidViewModel(application) {

    private val repository: PeopleRepository

    private var _isUserLoaded: MutableLiveData<Boolean> = MutableLiveData(false)
    val isUserLoaded: LiveData<Boolean>
        get() = _isUserLoaded

    private var _readAll: MutableLiveData<List<User>> = MutableLiveData()
    val readAll: LiveData<List<User>>
        get() = _readAll

    init {
        val userDAO = UserDatabase.getDatabase(application).userDao()
        repository = PeopleRepository(userDAO)
        _readAll = repository.readAll
    }

    fun loadUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadUsers()
            _isUserLoaded.postValue(true)
        }
    }

    fun readAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getUsers()
        }
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PeopleListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PeopleListViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}