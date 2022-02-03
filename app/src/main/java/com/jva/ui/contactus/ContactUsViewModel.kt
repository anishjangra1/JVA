package com.jva.ui.contactus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactUsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Contact Us"
    }
    val text: LiveData<String> = _text
}