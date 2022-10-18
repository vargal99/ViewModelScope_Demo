package com.example.android.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.viewmodelscopedemo.model.User
import com.example.android.viewmodelscopedemo.model.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private var userRepository = UserRepository()
    var users : MutableLiveData<List<User>> = MutableLiveData()

    private val myJob = Job()

    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        myScope.launch {

            var result : List<User>? = null
            withContext(Dispatchers.IO){
                result = userRepository.getUsers()
            }
            users.value = result

        }
    }

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }
}