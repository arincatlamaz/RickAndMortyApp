package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Character Detail"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.txtIdCharacter.text = character.id.toString()
        binding.txtStatusDetail.text = character.status
        Picasso.get().load(character.image).into(binding.imgCharacter)
        binding.txtName.text = character.name
        binding.txtSpecie.text = character.species
        binding.txtGender.text = character.gender
        binding.txtNEpisodes.text = character.episode.size.toString()
        binding.txtOrigin.text = character.origin.name
        binding.txtLocation.text = character.location.name

    }

}