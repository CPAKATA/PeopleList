package com.example.listofpeople.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val avatar: String,
): Parcelable