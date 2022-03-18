package com.example.pokedex

data class PokemonPageDetails(
    val next: String,
    val previous: String,
    val results: List<PokemonListItem>
)