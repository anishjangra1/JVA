package com.jva.remote

import com.jva.data.model.*
import com.jva.utils.Constants
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface JVAService {


    @GET(Constants.CONTACTS_DETAILS)
    suspend fun getContacts(): Response<ContactDetailResponse>

    @FormUrlEncoded
    @POST(Constants.DOCUMENTS)
    suspend fun getDocuments(
        @Field("client_id") client_id: String
    ): Response<DocumentCenterResponse>

    @POST(Constants.SERVICES)
    suspend fun getServices(): Response<ServiceResponse>

    @POST(Constants.ORGANIZATION_TYPE)
    suspend fun getOrganizationType(): Response<OrganiztionType>

    @GET(Constants.SELF_HELP_CATEGORY)
    suspend fun getSelfHelp(): Response<SelfHelpCategoryResponse>

    @FormUrlEncoded
    @POST(Constants.SELF_HELP_SUB_CATEGORY)
    suspend fun getSelf_SubHelp(
        @Field("category_id") category_id: Int,
    ): Response<SelfHelpSubCategoryResponse>


    @FormUrlEncoded
    @POST(Constants.SELF_HELP_ALL_CATEGORY)
    suspend fun getSelf_AllHelp(
        @Field("category_id") category_id: Int,
        @Field("sub_category ") sub_category : Int,
    ): Response<SelfHelpAllCategory>


    @GET(Constants.NEWS)
    suspend fun getNews(): Response<NewsResponse>

    @GET(Constants.KNOWLEDGE_CENTER)
    suspend fun getknowledge(): Response<KnowledgeCenter>

    @Multipart
    @POST(Constants.LOGIN)
    suspend fun getUser(@PartMap map: HashMap<String, RequestBody>): Response<UserDetail>


    @FormUrlEncoded
    @POST(Constants.LOGIN)
    suspend fun verifyOtp(
        @Field("userid") userid: String,
        @Field("password") password: String,
    ): Response<UserDetail>


    @FormUrlEncoded
    @POST(Constants.TICKET)
    suspend fun sendTicket(
        @Field("status") status: Int,
        @Field("category") category: String,
        @Field("details") details: String,
        @Field("client_id") client_id: String,
    ): Response<TicketResponse>


    @FormUrlEncoded
    @POST(Constants.REGISTER)
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("specific_product") specific_product: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
    ): Response<RegisterUserResponse>

}
