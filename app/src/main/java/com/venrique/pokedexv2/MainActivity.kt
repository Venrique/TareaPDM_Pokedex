package com.venrique.pokedexv2

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.venrique.pokedexv2.fragments.PokemonDetailFragment
import com.venrique.pokedexv2.fragments.PokemonListFragment
import com.venrique.pokedexv2.interfaces.IComunicaFragments
import com.venrique.pokedexv2.models.Pokemon

class MainActivity : AppCompatActivity(), PokemonListFragment.OnFragmentInteractionListener, PokemonDetailFragment.OnFragmentInteractionListener, IComunicaFragments {
    override fun enviarPersonaje(pokemon: Pokemon) {

        if(findViewById<LinearLayout>(R.id.contenedorFragment)==null){
            detalleFragment = this.supportFragmentManager.findFragmentById(R.id.fragDetail) as PokemonDetailFragment
            detalleFragment.asignarInfo(pokemon)
        }else{
            detalleFragment = PokemonDetailFragment()

            var bundleEnvio = Bundle()
            bundleEnvio.putSerializable("objeto",pokemon)

            detalleFragment.arguments = bundleEnvio

            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragment,detalleFragment).addToBackStack(null).commit()
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var listaFragment:PokemonListFragment
    lateinit var detalleFragment:PokemonDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaFragment = PokemonListFragment()

        if(findViewById<LinearLayout>(R.id.contenedorFragment)!=null){
            if(savedInstanceState!=null){
                return
            }
            supportFragmentManager.beginTransaction().replace(R.id.contenedorFragment,listaFragment).commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.fragList,listaFragment).commit()
        }
    }
}
