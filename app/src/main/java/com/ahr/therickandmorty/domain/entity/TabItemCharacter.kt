package com.ahr.therickandmorty.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TabItemCharacter(
    val title: String,
    val species: String
) : Parcelable
