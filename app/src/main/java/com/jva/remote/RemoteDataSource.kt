package com.jva.remote

import com.jva.utils.AppPreferences
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val jvaService: JVAService) {
    @Inject
    lateinit var appPreferences: AppPreferences

    suspend fun getknowledge() =  jvaService.getknowledge()
    suspend fun getDocuments() =  jvaService.getDocuments("C0017")
    suspend fun getServices() =  jvaService.getServices()
    suspend fun getOrganizationType() =  jvaService.getOrganizationType()
    suspend fun getSelfHelp() =  jvaService.getSelfHelp()
    suspend fun getSelfSubHelp(categoryId: Int) =  jvaService.getSelf_SubHelp(categoryId)
    suspend fun getSelfAllHelp(categoryId: Int, subcategoryId: Int) =  jvaService.getSelf_AllHelp(categoryId,subcategoryId)
    suspend fun getNews() =  jvaService.getNews()
    suspend fun getContacts() =  jvaService.getContacts()
    suspend fun postLogin(userid: String,password:String) =  jvaService.verifyOtp(userid,password)
    suspend fun postTicket(query: String) =  jvaService.sendTicket(1,"Accounting",query, appPreferences.getUserId())
    suspend fun register(hashMap: HashMap<String,String>) =  jvaService.registerUser("names","diil@gmail.com","1","9891432000","1234")
}
