package com.example.listofpeople

import android.text.TextUtils
import com.example.listofpeople.preview.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class UtilsTest {

    @Test
    fun checkInput() {
        // mock
        val name = "aaa"
        val surname = "bbb"
        val email = "ccc"
        // action
        val result = Utils.checkInput(name, surname, email)
        // verify
        assertTrue(result)
    }

    @Test
    fun checkInput_incorrect(){
        // mock
        val name = ""
        val surname = ""
        val email = ""
        // action
        val result = Utils.checkInput(name, surname, email)
        // verify
        assertFalse(result)
    }

    @Test
    fun checkInput_incorrect2(){
        // mock
        val name = ""
        val surname = "sds"
        val email = ""
        // action
        val result = Utils.checkInput(name, surname, email)
        // verify
        assertFalse(result)
    }
}