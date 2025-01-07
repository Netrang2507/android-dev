package com.example.mvvmapp

import kotlinx.coroutines.delay

class UserRepo {
   suspend fun fetechUserData():UserData{
        delay(2000)
        return UserData("Netrang ",21)
    }
}