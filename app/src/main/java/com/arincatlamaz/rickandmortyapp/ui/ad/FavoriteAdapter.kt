package com.arincatlamaz.rickandmortyapp.ui.ad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FavoriteListItemBinding
import com.arincatlamaz.rickandmortyapp.model.Character
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.arincatlamaz.rickandmortyapp.ui.fragment.ListFragmentDirections
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val favoriteList: ArrayList<Favorite> = ArrayList()) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var listCharacters = emptyList<Character>()

    class FavoriteViewHolder(binding: FavoriteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var character_imgFav = binding.characterImgFav
        var txt_id_characterFav = binding.txtIdCharacterFav
        var txt_name_characterFav = binding.txtNameCharacterFav
        var txt_statusDetailFav = binding.txtStatusDetailFav

        fun bind(favorite: Favorite) {
            Picasso.get().load(favorite.Image).into(character_imgFav)
            txt_statusDetailFav.text = favorite.Status
            txt_id_characterFav.text = favorite.Position.toString()
            txt_name_characterFav.text = favorite.Name
        }

    }

    fun setFavorites(favorites: List<Favorite>) {
        favoriteList.clear()
        favoriteList.addAll(favorites)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavoriteListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {

        holder.bind(favoriteList[position])

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

}