package com.arincatlamaz.rickandmortyapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.arincatlamaz.rickandmortyapp.R
import com.arincatlamaz.rickandmortyapp.databinding.FragmentFilterBinding
import com.arincatlamaz.rickandmortyapp.service.Repository
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModel
import com.arincatlamaz.rickandmortyapp.ui.vm.SharedViewModelFactory
import com.arincatlamaz.rickandmortyapp.util.getTextButtonChecked
import com.arincatlamaz.rickandmortyapp.util.getTextChipChecked
import com.arincatlamaz.rickandmortyapp.util.setButtonChecked
import com.arincatlamaz.rickandmortyapp.util.setChipChecked
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {
    private val viewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(Repository()) }
    private lateinit var binding: FragmentFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.filterValue.observe(viewLifecycleOwner) {
            binding.chipgroupStatus.setChipChecked(it[0])
            binding.radiogroupGender.setButtonChecked(it[1])
        }

        binding.btnMakeFilter.setOnClickListener {
            if (binding.chipgroupStatus.getTextChipChecked()
                    .isNotEmpty() && binding.radiogroupGender.getTextButtonChecked().isNotEmpty()
            ) {
                viewModel.getByStatusAndGender(
                    binding.chipgroupStatus.getTextChipChecked(),
                    binding.radiogroupGender.getTextButtonChecked(),
                    1
                )
            } else {
                if (binding.chipgroupStatus.getTextChipChecked().isNotEmpty()) {
                    viewModel.getByStatus(binding.chipgroupStatus.getTextChipChecked(), 1)
                } else {
                    viewModel.getByGender(binding.radiogroupGender.getTextButtonChecked(), 1)
                }
            }

            viewModel.filterValue.value = arrayOf(
                binding.chipgroupStatus.checkedChipId,
                binding.radiogroupGender.checkedRadioButtonId
            )

            findNavController().popBackStack(R.id.listFragment, false)
        }
    }
}