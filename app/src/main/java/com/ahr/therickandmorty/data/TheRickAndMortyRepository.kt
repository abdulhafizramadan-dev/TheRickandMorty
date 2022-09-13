package com.ahr.therickandmorty.data

import com.ahr.therickandmorty.data.remote.RemoteDataSource
import com.ahr.therickandmorty.domain.Response
import com.ahr.therickandmorty.domain.entity.Character
import com.ahr.therickandmorty.domain.entity.ForYou
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TheRickAndMortyRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun getForYouContent(): Flow<Response<Map<String, List<ForYou>>>> = flow {
        emit(Response.Loading)
        when (val result = remoteDataSource.getForYouContent()) {
            is Result.Success -> {
                emit(Response.Success(result.data))
            }
            is Result.Error -> {
                emit(Response.Error(result.message))
            }
        }
    }

    fun getCharacterList(species: String?): Flow<Response<List<Character>>> = flow {
        emit(Response.Loading)
        when (val result = remoteDataSource.getCharacterList(species)) {
            is Result.Success -> {
                emit(Response.Success(result.data))
            }
            is Result.Error -> {
                emit(Response.Error(result.message))
            }
        }
    }
}