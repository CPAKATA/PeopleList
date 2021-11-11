package com.example.listofpeople.repository

import androidx.lifecycle.MutableLiveData
import com.example.listofpeople.api.API
import com.example.listofpeople.data.User
import com.example.listofpeople.data.UserDAO
import com.example.listofpeople.preview.utils.Utils.convertToUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeopleRepository(private val userDAO: UserDAO) {

    val readAll: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun loadUsers(){
        withContext(Dispatchers.IO){
            userDAO.addUsers(convertToUser(API.people.getPeople()))
        }
    }

    suspend fun getUsers(){
        withContext(Dispatchers.IO){
            readAll.postValue(userDAO.readAll())
        }
    }

    suspend fun updateUser(user: User){
        withContext(Dispatchers.IO){
            userDAO.updateUser(user)
        }
    }

    suspend fun deleteUser(user: User){
        withContext(Dispatchers.IO){
            userDAO.deleteUser(user)
        }
    }
}