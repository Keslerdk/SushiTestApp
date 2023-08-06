package com.example.sushitestapp.presentor.list

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sushitestapp.databinding.CarListFragmentBinding
import com.example.sushitestapp.presentor.list.state.ListStateEvent
import com.example.sushitestapp.utils.Constants.NON_CAR_SELECTED_ID
import com.example.sushitestapp.utils.Constants.SELECTED_ITEM_ID_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListFragment : Fragment() {

    private var _binding: CarListFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter get() = binding.rvList.adapter as CarListAdapter
    private val viewModel: CarListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter =
            CarListAdapter { id -> viewModel.handleStateEvent(ListStateEvent.SetSelectedCar(id)) }
        viewModel.handleStateEvent(ListStateEvent.GetCars())
        binding.fabAdd.setOnClickListener { viewModel.handleStateEvent(ListStateEvent.AddNewCar) }
        initObservers()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.isLoading.also {
                binding.pbLoading.isVisible = it
                binding.rvList.isVisible = !it
                binding.fabAdd.isVisible = !it
            }

            viewState.cars?.let { adapter.submitList(it) }
        }

        viewModel.navigationEvent.observeEvent(viewLifecycleOwner) { id ->
            id?.let {
                findNavController().navigate(it, Bundle().apply {
                    putLong(SELECTED_ITEM_ID_KEY, viewModel.viewState.value?.selectedCar ?: -1)
                })
                viewModel.handleStateEvent(ListStateEvent.SetSelectedCar(NON_CAR_SELECTED_ID))
            }
        }
    }
}