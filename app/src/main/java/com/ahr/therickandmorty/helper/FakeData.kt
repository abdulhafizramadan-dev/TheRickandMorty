package com.ahr.therickandmorty.helper

import com.ahr.therickandmorty.domain.entity.Character

object FakeData {

    fun getDataForYou(): Map<String, List<Character>> {
        val mapForYouValue = mutableMapOf<String, List<Character>>()
        mapForYouValue["Human"] = getCharacters("Human")
        mapForYouValue["Humanoid"] = getCharacters("Humanoid")
        mapForYouValue["Alien"] = getCharacters("Alien")
        mapForYouValue["Robot"] = getCharacters("Robot")
        mapForYouValue["Poopybutthole"] = getCharacters("Poopybutthole")
        mapForYouValue["MythologicalCreature"] = getCharacters("Mythological Creature")
        mapForYouValue["Unknowns"] = getCharacters("Unknowns")
        return mapForYouValue
    }

    fun getDataCharacters(species: String): List<Character> {
        return getCharacters(species)
    }

    private fun getCharacters(species: String): List<Character> {
        return (1..10).map {
            Character(
                id = "$it",
                name = "Rick Sanchez",
                type = "Genetic experiment",
                status = "Alive",
                species = species,
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        }
    }
}