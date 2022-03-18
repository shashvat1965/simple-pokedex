package com.example.pokedex
import retrofit2.http.GET
import retrofit2.Call

interface PokeApiService {

    @GET("pokemon/?offset=0&limit=1126")
    fun getPokemon(): Call<PokemonPageDetails>


}