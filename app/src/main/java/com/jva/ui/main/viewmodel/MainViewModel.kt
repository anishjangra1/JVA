package com.jva.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jva.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel() : ViewModel() {

//    fun getUsers() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = mainRepository.getUsers()))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

   private val filterValues = HashMap<String,String>()



//    fun getUsers() = liveData(Dispatchers.IO) {
//        filterValues["userId"] = "2211454534"
//        filterValues["password"] = "1234"
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = mainRepository.postLogin(filterValues)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
}