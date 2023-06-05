package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.model.Favorite
import com.arincatlamaz.rickandmortyapp.model.User
import com.arincatlamaz.rickandmortyapp.service.Repository
import com.arincatlamaz.rickandmortyapp.ui.MainActivity
import com.arincatlamaz.rickandmortyapp.ui.ad.CharacterAdapter
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModel
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModelFactory

class ListFragment : Fragment(R.layout.fragment_list) {


    private val viewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(Repository()) }
    var adapter = CharacterAdapter()
    var userclass = User()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listCharactersInEpisode.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }


        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)
        val btnFilter = view.findViewById<ImageButton>(R.id.btn_filter)
        val txtReset = view.findViewById<TextView>(R.id.txt_reset)
        val favListBtn = view.findViewById<ImageButton>(R.id.favListBtn)

        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerview.adapter = adapter

        /*var ls = adapter.favList

        var exlist = ArrayList<Favorite>()

        for (i in 0 until adapter.favList.size){
            Log.d("FAVORITE LIST:", adapter.favList[i].name.toString())
            exlist.add(adapter.favList[i])

        }

        Log.d("EX LIST:", exlist.size.toString())
        Log.d("FAVORITE LIST SIZE:", ls.toString())
*/


        btnFilter.setOnClickListener {
            findNavController().navigate(R.id.listToFilter)
        }

        favListBtn.setOnClickListener {
            findNavController().navigate(R.id.listToFav)
        }

        getNameSearchView()

        viewModel.isFilter.observe(viewLifecycleOwner) {
            txtReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        txtReset.setOnClickListener {
            viewModel.getCharacters(1)
            viewModel.filterValue.value = arrayOf(0, 0)
        }
    }

    private fun getNameSearchView() {

        val searchView = view?.findViewById<SearchView>(R.id.searchView)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getByName(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getCharacters(1)
    }

}