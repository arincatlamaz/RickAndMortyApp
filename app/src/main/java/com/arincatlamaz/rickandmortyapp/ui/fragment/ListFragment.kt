package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FragmentListBinding
import com.arincatlamaz.rickandmortyapp.service.Repository
import com.arincatlamaz.rickandmortyapp.ui.ad.CharacterAdapter
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModel
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModelFactory

class ListFragment : Fragment(R.layout.fragment_list) {


    private val viewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(Repository()) }
    var adapter = CharacterAdapter()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        viewModel.listCharactersInEpisode.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }

        binding.recyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerview.adapter = adapter

        binding.btnFilter.setOnClickListener {
            findNavController().navigate(R.id.listToFilter)
        }

        binding.favListBtn.setOnClickListener {
            findNavController().navigate(R.id.listToFav)
        }

        getNameSearchView()

        viewModel.isFilter.observe(viewLifecycleOwner) {
            binding.txtReset.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        binding.txtReset.setOnClickListener {
            viewModel.getCharacters(1)
            viewModel.filterValue.value = arrayOf(0, 0)
        }
        return binding.root
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