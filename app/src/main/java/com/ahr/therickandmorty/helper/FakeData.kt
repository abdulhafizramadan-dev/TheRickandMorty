package com.ahr.therickandmorty.helper

import com.ahr.therickandmorty.domain.entity.Character

object FakeData {

    fun getData(): Map<String, List<Character>> {
        val mapCharacters = mutableMapOf<String, List<Character>>()
        mapCharacters["Human"] = getCharacters("Human")
        mapCharacters["Humanoid"] = getCharacters("Humanoid")
        mapCharacters["Alien"] = getCharacters("Alien")
        mapCharacters["Animal"] = getCharacters("Animal")
        mapCharacters["Robot"] = getCharacters("Robot")
        return mapCharacters
    }

    private fun getCharacters(species: String): List<Character> {
        return (1..10).map {
            Character(
                id = "$it",
                name = "Rick Sanchez",
                type = "Genetic experiment",
                status = "Alive",
                species = species,
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        }
    }
}