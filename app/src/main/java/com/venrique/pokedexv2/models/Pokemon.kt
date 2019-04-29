package com.venrique.pokedexv2.models

import java.io.Serializable

data class Pokemon
    (val id: Int, val nombre: String, val tipos: ArrayList<Int>, val peso: Double, val altura: Double,val imagen: String, val habilidades: ArrayList<String>)
    :Serializable
