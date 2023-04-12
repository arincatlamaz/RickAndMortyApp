package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FragmentFavoriteBinding
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.arincatlamaz.rickandmortyapp.ui.ad.CharacterAdapter
import com.arincatlamaz.rickandmortyapp.ui.ad.FavoriteAdapter
import java.lang.String
import java.util.*

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    var adapter = FavoriteAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        binding.recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerview.adapter = adapter
        adapter.setFavorites(adapter.favoriteList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val favorite = args.favorite

        binding.txtNameFav.text = favorite.name*/


    }





}