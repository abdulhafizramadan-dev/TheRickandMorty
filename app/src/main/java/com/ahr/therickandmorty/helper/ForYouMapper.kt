package com.ahr.therickandmorty.helper

import com.ahr.therickandmorty.ForYouContentQuery
import com.ahr.therickandmorty.domain.entity.ForYou

object ForYouMapper {
    fun mapToDomainHuman(data: List<ForYouContentQuery.Result?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
    fun mapToDomainHumanoid(data: List<ForYouContentQuery.Result1?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
    fun mapToDomainAlien(data: List<ForYouContentQuery.Result2?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
    fun mapToDomainRobot(data: List<ForYouContentQuery.Result3?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
    fun mapToDomainPoopybutthole(data: List<ForYouContentQuery.Result4?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
    fun mapToDomainUnknown(data: List<ForYouContentQuery.Result5?>?): List<ForYou> {
        if (data == null) return emptyList()
        return data.map {
            ForYou(
                id = it?.id ?: "",
                name = it?.name ?: "",
                status = it?.status ?: "",
                type = it?.type ?: "",
                species = it?.species ?: "",
                image = it?.image ?: ""
            )
        }
    }
}