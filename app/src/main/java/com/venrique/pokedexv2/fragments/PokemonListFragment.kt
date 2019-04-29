package com.venrique.pokedexv2.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.nfc.Tag
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.venrique.pokedexv2.R
import com.venrique.pokedexv2.adapter.PokemonAdapter
import com.venrique.pokedexv2.interfaces.IComunicaFragments
import com.venrique.pokedexv2.models.Pokemon
import com.venrique.pokedexv2.utils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*
import org.json.JSONObject
import java.io.IOException




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var listaPokemons: ArrayList<Pokemon> = ArrayList()
lateinit var recyclerPokemons: RecyclerView
lateinit var btnSearch: Button
lateinit var pokemonType_et: EditText
lateinit var pokeEstado: TextView
var bandera = true
var contador = 0
private val TAG = PokemonListFragment::class.java.simpleName

private lateinit var pokeAdapter: PokemonAdapter
private lateinit var viewManager: RecyclerView.LayoutManager

lateinit var actividad: Activity
lateinit var interfaceComunicaFragments: IComunicaFragments

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PokemonListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PokemonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PokemonListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista: View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        initRecycler(vista)

        btnSearch = vista.findViewById(R.id.search)
        pokemonType_et = vista.findViewById(R.id.pokeType)
        pokeEstado = vista.findViewById(R.id.state)

        btnSearch.setOnClickListener(View.OnClickListener {

            if(pokemonType_et!=null && pokemonType_et.text.toString()!="" && bandera){
                Toast.makeText(context, pokemonType_et.text.toString(), Toast.LENGTH_LONG).show()
                fetchPokemon().execute(pokemonType_et.text.toString())
            }else {
                Toast.makeText(context, "Escriba un tipo o espere a que termine de cargar", Toast.LENGTH_LONG).show()
            }
        })

        return vista
    }


    private inner class fetchPokemon : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg pokemonNumbers: String): String? {
            bandera=false

            if (pokemonNumbers.isEmpty()) {
                return null
            }

            val ID = pokemonNumbers[0]

            val pokeAPI = NetworkUtils.buildUrl(ID,"type")

            try {
                return NetworkUtils.getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }

        }

        override fun onPostExecute(pokemonInfo: String?) {
            if (pokemonInfo != null && pokemonInfo != "") {
                listaPokemons.clear()
                var jsonPokemons = JSONObject(pokemonInfo)
                var pokeArray = jsonPokemons.getJSONArray("pokemon")

                var listaP = mutableListOf<String>()

                for (i in 0..pokeArray.length()-1) listaP.add(i,pokeArray.getJSONObject(i).getJSONObject("pokemon").getString("name"))


                listaP.forEach { element ->
                    fetchFullPokemon().execute(element,listaP.size.toString())
                }
                //pokeArray.getJSONObject(0).getJSONObject("pokemon").getString("name")
            }else{
                Toast.makeText(context, "Ingrese un tipo valido", Toast.LENGTH_LONG).show()
                bandera=true
            }
        }
    }

    private inner class fetchFullPokemon : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg pokemonNumbers: String): String? {

            if (pokemonNumbers.isEmpty()) {
                return null
            }

            activity?.runOnUiThread(java.lang.Runnable {
                pokeEstado.setText("cargando pokemons...")
            })

            contador++
            if (contador == pokemonNumbers[1].toInt()){
                bandera=true
                contador=0
                activity?.runOnUiThread(java.lang.Runnable {
                    pokeEstado.setText("terminado!")
                })
            }

            val ID = pokemonNumbers[0]

            val pokeAPI = NetworkUtils.buildUrl(ID,"pokemon")

            try {
                return NetworkUtils.getResponseFromHttpUrl(pokeAPI)
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }

        }



        override fun onPostExecute(pokemonInfo: String?) {
            if (pokemonInfo != null && pokemonInfo != "") {
                var jsonPokemons = JSONObject(pokemonInfo)
                var pokeArray = jsonPokemons.getJSONArray("abilities")
                var pokeType = jsonPokemons.getJSONArray("types")

                val id = jsonPokemons.getInt("id")
                val nombre = jsonPokemons.getString("name")
                val tipos: ArrayList<Int> = ArrayList()

                for(e in 0..pokeType.length()-1){
                    when(pokeType.getJSONObject(e).getJSONObject("type").getString("name")){
                        "bug" -> tipos?.add(R.drawable.bugname)
                        "dark" -> tipos?.add(R.drawable.darkname)
                        "dragon" -> tipos?.add(R.drawable.dragonname)
                        "electric" -> tipos?.add(R.drawable.electricname)
                        "fairy" -> tipos?.add(R.drawable.fairyname)
                        "fighting" -> tipos?.add(R.drawable.fightingname)
                        "fire" -> tipos?.add(R.drawable.firename)
                        "flying" -> tipos?.add(R.drawable.flyingname)
                        "ghost" -> tipos?.add(R.drawable.ghostname)
                        "grass" -> tipos?.add(R.drawable.grassname)
                        "ground" -> tipos?.add(R.drawable.groundname)
                        "ice" -> tipos?.add(R.drawable.icename)
                        "normal" -> tipos?.add(R.drawable.normalname)
                        "poison" -> tipos?.add(R.drawable.poisonname)
                        "psychic" -> tipos?.add(R.drawable.psichicname)
                        "rock" -> tipos?.add(R.drawable.rockname)
                        "steel" -> tipos?.add(R.drawable.steelname)
                        "water" -> tipos?.add(R.drawable.watername)
                    }
                }

                val peso = jsonPokemons.getDouble("weight")/10.0
                val altura = jsonPokemons.getDouble("height")/10.0
                val imagen = jsonPokemons.getJSONObject("sprites").getString("front_default")
                val habilidades: ArrayList<String> = ArrayList()

                for (i in 0..pokeArray.length()-1) habilidades?.add(pokeArray.getJSONObject(i).getJSONObject("ability").getString("name"))

                Log.d(TAG,nombre)
                Log.d(TAG,tipos.toString())

                listaPokemons.add(Pokemon(id,nombre,tipos,peso,altura,imagen,habilidades))
                pokeAdapter.changeList(listaPokemons)
            }
        }
    }


    private fun initRecycler(vista:View) {

        viewManager = LinearLayoutManager(context)
        pokeAdapter = PokemonAdapter(listaPokemons)

        recyclerPokemons = vista.findViewById(R.id.recycler_poke)
        recyclerPokemons.setHasFixedSize(true)
        recyclerPokemons.layoutManager = viewManager
        recyclerPokemons.adapter = pokeAdapter


        pokeAdapter.setOnClickListener(View.OnClickListener {
            //Toast.makeText(context, listaPokemons.get(recyclerPokemons.getChildAdapterPosition(it)).nombre,Toast.LENGTH_SHORT).show()

            interfaceComunicaFragments.enviarPersonaje(listaPokemons.get(recyclerPokemons.getChildAdapterPosition(it)))
        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is Activity){
            actividad = context
            interfaceComunicaFragments = actividad as IComunicaFragments
        }

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokemonListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
