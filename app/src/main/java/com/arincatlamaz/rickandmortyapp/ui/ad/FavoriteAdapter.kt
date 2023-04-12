package com.arincatlamaz.rickandmortyapp.ui.ad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FavoriteListItemBinding
import com.arincatlamaz.rickandmortyapp.model.Character
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.squareup.picasso.Picasso

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var favoriteList = ArrayList<Favorite>()

    class FavoriteViewHolder(binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {


        var character_imgFav = binding.characterImgFav
        var txt_id_characterFav = binding.txtIdCharacterFav
        var txt_name_characterFav = binding.txtNameCharacterFav
        var txt_statusDetailFav = binding.txtStatusDetailFav

        fun bind(favorite: Favorite, context: Context?) {
            Picasso.get().load(favorite.image).into(character_imgFav)
            txt_statusDetailFav.text = favorite.status
            txt_id_characterFav.text = favorite.id.toString()
            txt_name_characterFav.text = favorite.name
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FavoriteListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(binding)
    }

    fun setFavorites(favorites: ArrayList<Favorite>) {
        favoriteList = favorites
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position],null)


    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

}