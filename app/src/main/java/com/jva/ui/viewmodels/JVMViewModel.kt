package com.jva.ui.viewmodels

import android.app.Application
import android.text.Editable
import androidx.lifecycle.*
import com.jva.data.model.*
import com.jva.data.repository.Repository
import com.jva.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JVMViewModel @Inject constructor (private val repository: Repository, application: Application) : AndroidViewModel(application) {
    val _responseDocument : MutableLiveData<String> =  MutableLiveData()
    val responseDocumentCenter : LiveData<String> = _responseDocument


    private val _responseDocumentCenterResponse: MutableLiveData<NetworkResult<DocumentCenterResponse>> =  MutableLiveData()
    val responseDocumentCenterResponse: LiveData<NetworkResult<DocumentCenterResponse>> = _responseDocumentCenterResponse

    private val _responseNews: MutableLiveData<NetworkResult<NewsResponse>> =  MutableLiveData()
    val responseNews: LiveData<NetworkResult<NewsResponse>> = _responseNews

    private val _responseService: MutableLiveData<NetworkResult<ServiceResponse>> =  MutableLiveData()
    val responseService: LiveData<NetworkResult<ServiceResponse>> = _responseService

    private val _responseSelfHelp: MutableLiveData<NetworkResult<SelfHelpCategoryResponse>> =  MutableLiveData()
    val responseSelfHelp: LiveData<NetworkResult<SelfHelpCategoryResponse>> = _responseSelfHelp

    private val _responseSelfSubHelp: MutableLiveData<NetworkResult<SelfHelpSubCategoryResponse>> =  MutableLiveData()
    val responseSelfSubHelp: LiveData<NetworkResult<SelfHelpSubCategoryResponse>> = _responseSelfSubHelp

    private val _responseSelfAllHelp: MutableLiveData<NetworkResult<SelfHelpAllCategory>> =  MutableLiveData()
    val responseSelfAllHelp: LiveData<NetworkResult<SelfHelpAllCategory>> = _responseSelfAllHelp

    private val _responseKnowledge: MutableLiveData<NetworkResult<KnowledgeCenter>> =  MutableLiveData()
    val responseKnowledge: LiveData<NetworkResult<KnowledgeCenter>> = _responseKnowledge

    private val _responseContacts: MutableLiveData<NetworkResult<ContactDetailResponse>> =  MutableLiveData()
    val responseContacts: LiveData<NetworkResult<ContactDetailResponse>> = _responseContacts

    private val _responseLogin: MutableLiveData<NetworkResult<UserDetail>> =  MutableLiveData()
    val responseLogin: LiveData<NetworkResult<UserDetail>> = _responseLogin

    private val _responseRegister: MutableLiveData<NetworkResult<RegisterUserResponse>> =  MutableLiveData()
    val responseRegister: LiveData<NetworkResult<RegisterUserResponse>> = _responseRegister


    private val _responseTicket: MutableLiveData<NetworkResult<TicketResponse>> =  MutableLiveData()
    val responseTicket: LiveData<NetworkResult<TicketResponse>> = _responseTicket


    fun getDocumentCenterResponse() = viewModelScope.launch {

        repository.getDocuments().collect { value: NetworkResult<DocumentCenterResponse> ->
            _responseDocumentCenterResponse.value = value
        }
    }


    fun getNewsResponse() = viewModelScope.launch {

        repository.getNews().collect { value: NetworkResult<NewsResponse> ->
            _responseNews.value = value
        }
    }


    fun getServicesResponse() = viewModelScope.launch {

        repository.getServices().collect { value: NetworkResult<ServiceResponse> ->
            _responseService.value = value
        }
    }


//    fun getOrganizationType() = viewModelScope.launch {
//
//        repository.getOrganizationType().collect { value: NetworkResult<ServiceResponse> ->
//            _responseService.value = value
//        }
//    }

    fun getSelfHelpResponse() = viewModelScope.launch {

        repository.getSelfHelp().collect { value: NetworkResult<SelfHelpCategoryResponse> ->
            _responseSelfHelp.value = value
        }
    }


    fun getSelfSubHelpResponse(categoryId: Int) = viewModelScope.launch {

        repository.getSelfSubHelp(categoryId).collect { value: NetworkResult<SelfHelpSubCategoryResponse> ->
            _responseSelfSubHelp.value = value
        }
    }


    fun getSelfAllHelpResponse(categoryId: Int, subcategoryId: Int) = viewModelScope.launch {

        repository.getSelfAllHelp(categoryId,subcategoryId).collect { value: NetworkResult<SelfHelpAllCategory> ->
            _responseSelfAllHelp.value = value
        }
    }

    fun getContactResponse() = viewModelScope.launch {

        repository.getContacts().collect { value: NetworkResult<ContactDetailResponse> ->
            _responseContacts.value = value
        }
    }

    fun fetchKnowledgeCenterResponse() = viewModelScope.launch {

        repository.getknowledgeCenter().collect { value: NetworkResult<KnowledgeCenter> ->
            _responseKnowledge.value = value
        }
    }


    fun fetchLoginDetailsResponse(userId :String,password: String) = viewModelScope.launch {

        repository.getLogin(userId,password).collect { value: NetworkResult<UserDetail> ->
            _responseLogin.value = value
        }
    }

    fun sendTicketResponse(query: String) = viewModelScope.launch {

        repository.getTicket(query).collect { value: NetworkResult<TicketResponse> ->
            _responseTicket.value = value
        }
    }

    fun registerUser(name: Editable, mobile: Editable, email: Editable?, specific_product: String, password: Editable) = viewModelScope.launch {
        val mapReq = hashMapOf<String, String>(
            "name" to name.toString(),
            "email" to email.toString(),
            "specific_product" to specific_product.toString(),
            "mobile" to mobile.toString(),
            "password" to password.toString(),
        )
        repository.registerUser(mapReq).collect { value: NetworkResult<RegisterUserResponse> ->
            _responseRegister.value = value
        }

    }

}