package com.arincatlamaz.rickandmortyapp.ui.ad

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.ItemListBinding
import com.arincatlamaz.rickandmortyapp.model.Character
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.arincatlamaz.rickandmortyapp.ui.fragment.ListFragmentDirections
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var listCharacters = emptyList<Character>()
    var favList : ArrayList<Favorite> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list, parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position],null)

        holder.favoriteBtn.setOnClickListener {view ->

            val variable = listCharacters.get(position).name
            Log.d("TAG",variable.toString())
            holder.favoriteBtn.setBackgroundResource(R.drawable.favorite_red)

            var fav = Favorite(listCharacters[position].id,listCharacters[position].name,
            listCharacters[position].status,listCharacters[position].image)

            var exp = favList.add(fav)

            Log.d("TAG EXP",favList.size.toString())

            val action = ListFragmentDirections.listToFav(listCharacters[position])


            view.findNavController().navigate(action)

        }

        holder.itemView.setOnClickListener { view ->
            val action = ListFragmentDirections.listToDetail(listCharacters[position])
            view.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<Character>) {
        listCharacters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        var favoriteBtn = binding.favoriteBtn
        var image_character = binding.characterImg
        var status_type = binding.txtStatusDetail
        var id_number = binding.txtIdCharacter
        var name_character = binding.txtNameCharacter


        fun bind(character: Character, context: Context?) {
            Picasso.get().load(character.image).into(image_character)
            status_type.text = character.status
            id_number.text = character.id.toString()
            name_character.text = character.name
        }


    }



}