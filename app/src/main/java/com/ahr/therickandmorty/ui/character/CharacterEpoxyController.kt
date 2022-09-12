package com.ahr.therickandmorty.ui.character

import com.ahr.therickandmorty.characterVertical
import com.ahr.therickandmorty.domain.entity.Character
import com.airbnb.epoxy.TypedEpoxyController

class CharacterEpoxyController : TypedEpoxyController<List<Character>>() {
    override fun buildModels(characters: List<Character>?) {
        characters?.forEach { character ->
            characterVertical {
                id(character.id)
                character(character)
            }
        }
    }
}