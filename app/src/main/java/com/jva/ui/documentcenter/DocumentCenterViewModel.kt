package com.jva.ui.documentcenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DocumentCenterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Document Bank"
    }
    val text: LiveData<String> = _text
}