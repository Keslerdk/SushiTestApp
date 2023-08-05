package com.example.sushitestapp.presentor.edit

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.databinding.EditCarFragmentBinding
import com.example.sushitestapp.presentor.edit.state.EditCarEvent
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.handleCoroutineException

@AndroidEntryPoint
class EditCarFragment : Fragment() {

    private var _binding: EditCarFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditCarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditCarFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener { onButtonClick() }
        initObservers()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            viewState.isLoading.also {
                binding.gInputFields.isVisible = !it
                binding.btnSave.isVisible = !it
                binding.pbLoading.isVisible = it
            }
        }
    }

    private fun onButtonClick() {
        val car = Car(
            image = binding.tilImageUrl.editText?.text?.toString() ?: "",
            price = binding.tilPrice.editText?.text?.toString()?.toInt() ?: 0,
            brand = binding.tilBrand.editText?.text?.toString() ?: "",
            model = binding.tilModel.editText?.text?.toString() ?: "",
            year = binding.tilYear.editText?.text?.toString()?.toInt() ?: 0,
            mileage = binding.tilMiles.editText?.text?.toString()?.toLong() ?: 0L,
            color = binding.tilColor.editText?.text?.toString() ?: "",
            vin = binding.tilVin.editText?.text?.toString() ?: ""
        )
        viewModel.handleEvent(EditCarEvent.AddNewCar(car))
    }
}