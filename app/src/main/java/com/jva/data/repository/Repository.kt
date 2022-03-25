package com.jva.data.repository
import com.jva.data.model.*
import com.jva.remote.RemoteDataSource
import com.jva.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {


    suspend fun getDocuments(): Flow<NetworkResult<DocumentCenterResponse>> {
        return flow<NetworkResult<DocumentCenterResponse>> {
            emit(safeApiCall { remoteDataSource.getDocuments() })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getServices(): Flow<NetworkResult<ServiceResponse>> {
        return flow<NetworkResult<ServiceResponse>> {
            emit(safeApiCall { remoteDataSource.getServices() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getOrganizationType(): Flow<NetworkResult<OrganiztionType>> {
        return flow<NetworkResult<OrganiztionType>> {
            emit(safeApiCall { remoteDataSource.getOrganizationType() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSelfHelp(): Flow<NetworkResult<SelfHelpCategoryResponse>> {
        return flow<NetworkResult<SelfHelpCategoryResponse>> {
            emit(safeApiCall { remoteDataSource.getSelfHelp() })
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getSelfSubHelp(categoryId: Int): Flow<NetworkResult<SelfHelpSubCategoryResponse>> {
        return flow<NetworkResult<SelfHelpSubCategoryResponse>> {
            emit(safeApiCall { remoteDataSource.getSelfSubHelp(categoryId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSelfAllHelp(categoryId: Int, subcategoryId: Int): Flow<NetworkResult<SelfHelpAllCategory>> {
        return flow<NetworkResult<SelfHelpAllCategory>> {
            emit(safeApiCall { remoteDataSource.getSelfAllHelp(categoryId,subcategoryId) })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getNews(): Flow<NetworkResult<NewsResponse>> {
        return flow<NetworkResult<NewsResponse>> {
            emit(safeApiCall { remoteDataSource.getNews() })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getContacts(): Flow<NetworkResult<ContactDetailResponse>> {
        return flow<NetworkResult<ContactDetailResponse>> {
            emit(safeApiCall { remoteDataSource.getContacts() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getknowledgeCenter(): Flow<NetworkResult<KnowledgeCenter>> {
        return flow<NetworkResult<KnowledgeCenter>> {
            emit(safeApiCall { remoteDataSource.getknowledge() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getLogin(userId: String, password: String): Flow<NetworkResult<UserDetail>> {
        return flow<NetworkResult<UserDetail>> {
            emit(safeApiCall { remoteDataSource.postLogin(userId,password) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTicket(query: String): Flow<NetworkResult<TicketResponse>> {
        return flow<NetworkResult<TicketResponse>> {
            emit(safeApiCall { remoteDataSource.postTicket(query) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun registerUser(hashMap: HashMap<String,String>): Flow<NetworkResult<RegisterUserResponse>> {
        return flow<NetworkResult<RegisterUserResponse>> {
            emit(safeApiCall { remoteDataSource.register(hashMap) })
        }.flowOn(Dispatchers.IO)
    }

}

