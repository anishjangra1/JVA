package com.jva.ui.knowledgeBank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KnowledgeBankViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Knowledge Bank"
    }
    val text: LiveData<String> = _text
}