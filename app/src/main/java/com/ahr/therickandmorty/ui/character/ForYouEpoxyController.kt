package com.ahr.therickandmorty.ui.character

import com.ahr.therickandmorty.CharacterHorizontalBindingModel_
import com.ahr.therickandmorty.domain.entity.Character
import com.ahr.therickandmorty.header
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel

class ForYouEpoxyController : TypedEpoxyController<Map<String, List<Character>>>() {
    override fun buildModels(data: Map<String, List<Character>>?) {
        data?.keys?.forEach { title ->
            header {
                id(title)
                title(title)
            }
            val characters = data[title]?.map { character ->
                CharacterHorizontalBindingModel_()
                    .id(character.id)
                    .character(character)
            }
            carousel {
                id("Carousel $title")
                if (characters != null) {
                    models(characters)
                }
            }
        }
    }
}