package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.arincatlamaz.rickandmortyapp.R
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        val txtIdCharacter = view.findViewById<TextView>(R.id.txt_id_character)
        val txtStatus = view.findViewById<TextView>(R.id.txt_statusDetail)
        val txtName = view.findViewById<TextView>(R.id.txt_name)
        val txtSpecie = view.findViewById<TextView>(R.id.txt_specie)
        val txtGender = view.findViewById<TextView>(R.id.txt_gender)
        val txtNEpisodes = view.findViewById<TextView>(R.id.txt_n_episodes)
        val txtOrigin = view.findViewById<TextView>(R.id.txt_origin)
        val txtLocation = view.findViewById<TextView>(R.id.txt_location)
        val imgCharacter = view.findViewById<ImageView>(R.id.img_character)

        txtIdCharacter.text= character.id.toString()
        txtStatus.text= character.status
        Picasso.get().load(character.image).into(imgCharacter)
        txtName.text = character.name
        txtSpecie.text = character.species
        txtGender.text = character.gender
        txtNEpisodes.text = character.episode.size.toString()
        txtOrigin.text = character.origin.name
        txtLocation.text = character.location.name

    }

}