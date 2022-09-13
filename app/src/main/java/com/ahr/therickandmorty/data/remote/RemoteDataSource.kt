package com.ahr.therickandmorty.data.remote

import com.ahr.therickandmorty.CharacterListQuery
import com.ahr.therickandmorty.ForYouContentQuery
import com.ahr.therickandmorty.data.Result
import com.ahr.therickandmorty.domain.entity.Character
import com.ahr.therickandmorty.domain.entity.ForYou
import com.ahr.therickandmorty.helper.ForYouMapper
import com.ahr.therickandmorty.helper.mapToDomain
import com.ahr.therickandmorty.type.FilterCharacter
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getForYouContent(): Result<Map<String, List<ForYou>>> {
        return try {
            val response = apolloClient.query(ForYouContentQuery()).execute()
            val mapForYouContent = mutableMapOf<String, List<ForYou>>()

            if (!response.hasErrors()) {
                mapForYouContent["Human"] = ForYouMapper.mapToDomainHuman(response.data?.human?.results)
                mapForYouContent["Humanoid"] = ForYouMapper.mapToDomainHumanoid(response.data?.humanoid?.results)
                mapForYouContent["Alien"] = ForYouMapper.mapToDomainAlien(response.data?.alien?.results)
                mapForYouContent["Robot"] = ForYouMapper.mapToDomainRobot(response.data?.robot?.results)
                mapForYouContent["Poopybutthole"] = ForYouMapper.mapToDomainPoopybutthole(response.data?.poopybutthole?.results)
                mapForYouContent["Unknown"] = ForYouMapper.mapToDomainUnknown(response.data?.Unknown?.results)
                Result.Success(mapForYouContent)
            } else {
                val isErrorEmpty = response.errors?.isNotEmpty()
                if (isErrorEmpty == true) {
                    Result.Error(response.errors?.get(0)?.message)
                } else {
                    Result.Error("Ops.. Unknown error!")
                }
            }
        } catch (exception: ApolloException) {
            Result.Error(exception.message)
        }
    }

    suspend fun getCharacterList(species: String?) : Result<List<Character>> {
        return try {
            val response = apolloClient.query(CharacterListQuery(filter = Optional.Present(
                FilterCharacter(species = Optional.present(species))
            ))).execute()
            val characterListResult = response.data?.characters?.results

            if (!response.hasErrors()) {
                val characterList = characterListResult?.mapToDomain() ?: emptyList()
                Result.Success(characterList)
            } else {val isErrorEmpty = response.errors?.isNotEmpty()
                if (isErrorEmpty == true) {
                    Result.Error(response.errors?.get(0)?.message)
                } else {
                    Result.Error("Ops.. Unknown error!")
                }
            }
        } catch (exception: ApolloException) {
            Result.Error(exception.message)
        }
    }
}