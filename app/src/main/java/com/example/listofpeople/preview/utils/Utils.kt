package com.example.listofpeople.preview.utils

import android.text.TextUtils
import com.example.listofpeople.api.dto.People
import com.example.listofpeople.data.User

object Utils {
    fun checkInput(name: String, surname: String, email: String) =
        name.isNotEmpty() && surname.isNotEmpty() && email.isNotEmpty()

    fun convertToUser(people: People): List<User>{
        val data = people.data
        val listUser = mutableListOf<User>()
        for (i in data){
            listUser.add(
                User(i.id, i.first_name,
                i.last_name, i.email, i.avatar)
            )
        }
        return listUser
    }
}