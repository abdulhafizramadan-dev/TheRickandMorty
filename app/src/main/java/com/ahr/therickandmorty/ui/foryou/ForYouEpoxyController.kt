package com.ahr.therickandmorty.ui.foryou

import com.ahr.therickandmorty.CharacterHorizontalBindingModel_
import com.ahr.therickandmorty.domain.entity.ForYou
import com.ahr.therickandmorty.header
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel

class ForYouEpoxyController : TypedEpoxyController<Map<String, List<ForYou>>>() {
    override fun buildModels(data: Map<String, List<ForYou>>) {
        data.keys.forEach { title ->
            header {
                id(title)
                title(title)
            }
            val characters = data[title]?.map { forYou ->
                CharacterHorizontalBindingModel_()
                    .id(forYou.id)
                    .forYou(forYou)
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