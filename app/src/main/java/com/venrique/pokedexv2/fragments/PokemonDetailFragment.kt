package com.venrique.pokedexv2.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

import com.venrique.pokedexv2.R
import com.venrique.pokedexv2.models.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var pokeNombre:TextView
lateinit var pokeImagen: ImageView
lateinit var pokeLogoUno: ImageView
lateinit var pokeTipoUno: ImageView
lateinit var pokeLogoDos: ImageView
lateinit var pokeTipoDos: ImageView
lateinit var pokeId: TextView
lateinit var pokeHabili: TextView
lateinit var pokePeso: TextView
lateinit var pokeAltura: TextView
lateinit var pokeFondo: LinearLayout
lateinit var pokeDetalle: LinearLayout
lateinit var pokeDetalleLogo: LinearLayout

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PokemonDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PokemonDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PokemonDetailFragment : Fragment() {
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

        val vista = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        pokeNombre = vista.findViewById(R.id.nombreDetalle)
        pokeImagen = vista.findViewById(R.id.imagenDetalle)
        pokeLogoUno = vista.findViewById(R.id.logoUno)
        pokeTipoUno = vista.findViewById(R.id.tipoUnoDetalle)
        pokeLogoDos = vista.findViewById(R.id.logoDos)
        pokeTipoDos = vista.findViewById(R.id.tipoDosDetalle)
        pokeId = vista.findViewById(R.id.id)
        pokeHabili = vista.findViewById(R.id.habilidades)
        pokePeso = vista.findViewById(R.id.peso)
        pokeAltura = vista.findViewById(R.id.altura)
        pokeFondo = vista.findViewById(R.id.contenedorDetalle)
        pokeDetalle = vista.findViewById(R.id.detalleInfo)
        pokeDetalleLogo = vista.findViewById(R.id.detalleTipo)

        val objetoPokemons = arguments
        var pokemon:Pokemon? = null

        if (objetoPokemons != null){
            pokemon = objetoPokemons.getSerializable("objeto") as Pokemon

            asignarInfo(pokemon)
        }

        return vista
    }

    fun asignarInfo(pokemon: Pokemon) {
        pokeNombre.text = pokemon.nombre
        pokeImagen.let {
            Glide.with(this@PokemonDetailFragment).load(pokemon.imagen).into(it)
        }
        when(pokemon.tipos[0]){
            R.drawable.normalname -> {
                pokeLogoUno.setImageResource(R.drawable.normallogo)
                pokeTipoUno.setImageResource(R.drawable.normalname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorNormal))
            }
            R.drawable.bugname -> {
                pokeLogoUno.setImageResource(R.drawable.buglogo)
                pokeTipoUno.setImageResource(R.drawable.bugname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorBug))

            }
            R.drawable.darkname ->{
                pokeLogoUno.setImageResource(R.drawable.darklogo)
                pokeTipoUno.setImageResource(R.drawable.darkname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorDark))

            }
            R.drawable.dragonname ->{
                pokeLogoUno.setImageResource(R.drawable.dragonlogo)
                pokeTipoUno.setImageResource(R.drawable.dragonname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorDragon))

            }
            R.drawable.electricname ->{
                pokeLogoUno.setImageResource(R.drawable.electriclogo)
                pokeTipoUno.setImageResource(R.drawable.electricname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorElectric))

            }
            R.drawable.fightingname ->{
                pokeLogoUno.setImageResource(R.drawable.faightinglogo)
                pokeTipoUno.setImageResource(R.drawable.fightingname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorFighting))

            }
            R.drawable.fairyname ->{
                pokeLogoUno.setImageResource(R.drawable.fairylogo)
                pokeTipoUno.setImageResource(R.drawable.fairyname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorFairy))

            }
            R.drawable.firename ->{
                pokeLogoUno.setImageResource(R.drawable.firelogo)
                pokeTipoUno.setImageResource(R.drawable.firename)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorFire))

            }
            R.drawable.flyingname ->{
                pokeLogoUno.setImageResource(R.drawable.flyinglogo)
                pokeTipoUno.setImageResource(R.drawable.flyingname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorFlying))

            }
            R.drawable.ghostname -> {
                pokeLogoUno.setImageResource(R.drawable.ghostlogo)
                pokeTipoUno.setImageResource(R.drawable.ghostname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorGhost))

            }
            R.drawable.grassname ->{
                pokeLogoUno.setImageResource(R.drawable.grasslogo)
                pokeTipoUno.setImageResource(R.drawable.grassname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorGrass))

            }
            R.drawable.groundname ->{
                pokeLogoUno.setImageResource(R.drawable.groundlogo)
                pokeTipoUno.setImageResource(R.drawable.groundname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorGround))

            }
            R.drawable.icename ->{
                pokeLogoUno.setImageResource(R.drawable.icelogo)
                pokeTipoUno.setImageResource(R.drawable.icename)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorIce))

            }
            R.drawable.poisonname ->{
                pokeLogoUno.setImageResource(R.drawable.poisonlogo)
                pokeTipoUno.setImageResource(R.drawable.poisonname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorPoison))

            }
            R.drawable.psichicname ->{
                pokeLogoUno.setImageResource(R.drawable.psichiclogo)
                pokeTipoUno.setImageResource(R.drawable.psichicname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorPsychic))

            }
            R.drawable.rockname ->{
                pokeLogoUno.setImageResource(R.drawable.rocklogo)
                pokeTipoUno.setImageResource(R.drawable.rockname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorRock))

            }
            R.drawable.steelname ->{
                pokeLogoUno.setImageResource(R.drawable.steellogo)
                pokeTipoUno.setImageResource(R.drawable.steelname)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorSteel))

            }
            R.drawable.watername ->{
                pokeLogoUno.setImageResource(R.drawable.waterlogo)
                pokeTipoUno.setImageResource(R.drawable.watername)
                pokeFondo.setBackgroundColor(resources.getColor(R.color.colorWater))

            }

        }
        if (pokemon.tipos.size >1){
            when(pokemon.tipos[1]){
                R.drawable.normalname -> {
                    pokeLogoDos.setImageResource(R.drawable.normallogo)
                    pokeTipoDos.setImageResource(R.drawable.normalname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorNormal))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorNormal))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorNormal))
                }
                R.drawable.bugname -> {
                    pokeLogoDos.setImageResource(R.drawable.buglogo)
                    pokeTipoDos.setImageResource(R.drawable.bugname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorBug))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorBug))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorBug))
                }
                R.drawable.darkname ->{
                    pokeLogoDos.setImageResource(R.drawable.darklogo)
                    pokeTipoDos.setImageResource(R.drawable.darkname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorDark))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorDark))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorDark))
                }
                R.drawable.dragonname ->{
                    pokeLogoDos.setImageResource(R.drawable.dragonlogo)
                    pokeTipoDos.setImageResource(R.drawable.dragonname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorDragon))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorDragon))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorDragon))
                }
                R.drawable.electricname ->{
                    pokeLogoDos.setImageResource(R.drawable.electriclogo)
                    pokeTipoDos.setImageResource(R.drawable.electricname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorElectric))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorElectric))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorElectric))
                }
                R.drawable.fightingname ->{
                    pokeLogoDos.setImageResource(R.drawable.faightinglogo)
                    pokeTipoDos.setImageResource(R.drawable.fightingname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorFighting))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorFighting))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorFighting))
                }
                R.drawable.fairyname ->{
                    pokeLogoDos.setImageResource(R.drawable.fairylogo)
                    pokeTipoDos.setImageResource(R.drawable.fairyname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorFairy))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorFairy))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorFairy))
                }
                R.drawable.firename ->{
                    pokeLogoDos.setImageResource(R.drawable.firelogo)
                    pokeTipoDos.setImageResource(R.drawable.firename)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorFire))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorFire))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorFire))
                }
                R.drawable.flyingname ->{
                    pokeLogoDos.setImageResource(R.drawable.flyinglogo)
                    pokeTipoDos.setImageResource(R.drawable.flyingname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorFlying))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorFlying))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorFlying))
                }
                R.drawable.ghostname -> {
                    pokeLogoDos.setImageResource(R.drawable.ghostlogo)
                    pokeTipoDos.setImageResource(R.drawable.ghostname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorGhost))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorGhost))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorGhost))
                }
                R.drawable.grassname ->{
                    pokeLogoDos.setImageResource(R.drawable.grasslogo)
                    pokeTipoDos.setImageResource(R.drawable.grassname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorGrass))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorGrass))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorGrass))
                }
                R.drawable.groundname ->{
                    pokeLogoDos.setImageResource(R.drawable.groundlogo)
                    pokeTipoDos.setImageResource(R.drawable.groundname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorGround))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorGround))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorGround))
                }
                R.drawable.icename ->{
                    pokeLogoDos.setImageResource(R.drawable.icelogo)
                    pokeTipoDos.setImageResource(R.drawable.icename)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorIce))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorIce))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorIce))
                }
                R.drawable.poisonname ->{
                    pokeLogoDos.setImageResource(R.drawable.poisonlogo)
                    pokeTipoDos.setImageResource(R.drawable.poisonname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorPoison))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorPoison))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorPoison))
                }
                R.drawable.psichicname ->{
                    pokeLogoDos.setImageResource(R.drawable.psichiclogo)
                    pokeTipoDos.setImageResource(R.drawable.psichicname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorPsychic))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorPsychic))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorPsychic))
                }
                R.drawable.rockname ->{
                    pokeLogoDos.setImageResource(R.drawable.rocklogo)
                    pokeTipoDos.setImageResource(R.drawable.rockname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorRock))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorRock))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorRock))
                }
                R.drawable.steelname ->{
                    pokeLogoDos.setImageResource(R.drawable.steellogo)
                    pokeTipoDos.setImageResource(R.drawable.steelname)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorSteel))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorSteel))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorSteel))
                }
                R.drawable.watername ->{
                    pokeLogoDos.setImageResource(R.drawable.waterlogo)
                    pokeTipoDos.setImageResource(R.drawable.watername)
                    pokeDetalle.setBackgroundColor(resources.getColor(R.color.colorWater))
                    pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.colorWater))
                    pokeNombre.setBackgroundColor(resources.getColor(R.color.colorWater))
                }

            }
        }else{
            pokeDetalle.setBackgroundColor(resources.getColor(R.color.white))
            pokeDetalleLogo.setBackgroundColor(resources.getColor(R.color.white))
            pokeNombre.setBackgroundColor(resources.getColor(R.color.white))
        }
        pokeId.text = "Pokemon #"+pokemon.id
        pokeHabili.text = pokemon.habilidades.toString()
        pokePeso.text = "${pokemon.peso} Kg"
        pokeAltura.text = "${pokemon.altura} m"
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
         * @return A new instance of fragment PokemonDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
