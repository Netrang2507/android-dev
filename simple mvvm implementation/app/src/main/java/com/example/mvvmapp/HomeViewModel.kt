package com.example.mvvmapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val userRepo: UserRepo = UserRepo()
    private val _userData = MutableLiveData<UserData>()
    val  userData : LiveData<UserData> = _userData
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

            fun getUserData(){
                _isLoading.postValue(true)
                viewModelScope.launch {
                    val userResult = userRepo.fetechUserData()
                    _userData.postValue(userResult)
                    _isLoading.postValue(false)
                }
            }
}