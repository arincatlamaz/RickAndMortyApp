package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FragmentFavoriteBinding
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.arincatlamaz.rickandmortyapp.model.User
import com.arincatlamaz.rickandmortyapp.ui.ad.FavoriteAdapter
import com.arincatlamaz.rickandmortyapp.util.getSerialNum
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    var adapter = FavoriteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.recyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = FavoriteAdapter()
        binding.recyclerview.adapter = adapter
        fetchFavoriteList()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Favorites"
    }



    private fun parseFavoriteItems(favorites: List<String>): ArrayList<Favorite> {
        val favoriteItems = ArrayList<Favorite>()

        for (favorite in favorites) {
            val favoriteParts = favorite.split("\n")

            if (favoriteParts.size >= 4) {
                val position = favoriteParts[0].substringAfter(":")
                val name = favoriteParts[1].substringAfter(":")
                val image = favoriteParts[2].substringAfter(":")
                val status = favoriteParts[3].substringAfter(":")

                val favoriteItem = Favorite(position, name, status, image)
                favoriteItems.add(favoriteItem)
            }
        }

        return favoriteItems
    }

    private fun fetchFavoriteList() {
        val db = Firebase.firestore
        val docRef = db.collection("users").document(getSerialNum(requireContext()))

        docRef.get().addOnSuccessListener { documentSnapshot ->
            val favorites = documentSnapshot.toObject(User::class.java)?.favoriteList

            if (favorites != null) {
                val favoriteItems = parseFavoriteItems(favorites)
                adapter.setFavorites(favoriteItems)
            }
        }.addOnFailureListener { exception ->
            Log.e("FavoriteFragment", "Error fetching favorite list: ${exception.message}")
        }
    }


}