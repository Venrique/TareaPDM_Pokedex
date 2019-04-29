package com.venrique.pokedexv2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.venrique.pokedexv2.R
import com.venrique.pokedexv2.models.Pokemon
import kotlinx.android.synthetic.main.item_list.view.*

class PokemonAdapter(var items: ArrayList<Pokemon>): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>(), View.OnClickListener {

    private var listener: View.OnClickListener? = null
    private val TAG = PokemonAdapter::class.java.simpleName

    fun setOnClickListener(listener: View.OnClickListener){
        this.listener = listener
    }

    override fun onClick(v: View?) {
        listener?.onClick(v)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        view.setOnClickListener(this)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun changeList(items: ArrayList<Pokemon>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.txtNombre.setText(items[position].nombre)
        holder.tipo1.setImageResource(items[position].tipos[0])

        if(items[position].tipos.size>1){
            holder.tipo2.setImageResource(items[position].tipos[1])
        }else{
            holder.tipo2.setImageResource(0)
        }
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNombre = itemView.nombrePokemon
        var tipo1 = itemView.tipoUno
        var tipo2 = itemView.tipoDos
    }
}