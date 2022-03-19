package com.example.pokedex

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.pokedex.databinding.ActivityMainBinding
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getPokemonData()
        binding.textView.movementMethod = ScrollingMovementMethod()
    }

    private fun getPokemonData() {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()

        var pokeApi = retrofit.create(PokeApiService::class.java)

        var call: Call<PokemonPageDetails> = pokeApi.getPokemon()

        call.enqueue(object : Callback<PokemonPageDetails> {
            override fun onResponse(call: Call<PokemonPageDetails>, response: Response<PokemonPageDetails>){
                val responseBody = response.body()!!
                val stringBuilder = StringBuilder()
                for(pokemonData in responseBody.results.listIterator()){
                    stringBuilder.append(pokemonData.name)
                                 .append("\n")
                }
                binding.textView.text = stringBuilder
            }

            override fun onFailure(call: Call<PokemonPageDetails>, tt: Throwable) {
                binding.textView.text = tt.message
            }
        })
    }
}