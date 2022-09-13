package com.ahr.therickandmorty.helper

import com.ahr.therickandmorty.CharacterListQuery
import com.ahr.therickandmorty.domain.entity.Character

fun CharacterListQuery.Result?.toDomain(): Character =
    Character(
        id = this?.id ?: "",
        name = this?.name ?: "",
        type = this?.type ?: "",
        status = this?.status ?: "",
        species = this?.species ?: "",
        gender = this?.gender ?: "",
        image = this?.image ?: ""
    )

fun List<CharacterListQuery.Result?>?.mapToDomain(): List<Character> {
    if (this == null) return emptyList()
    return map {
        it.toDomain()
    }
}