package com.example.sushitestapp.presentor.edit

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.databinding.EditCarFragmentBinding
import com.example.sushitestapp.presentor.edit.state.EditCarEvent
import com.example.sushitestapp.utils.Constants.NON_CAR_SELECTED_ID
import com.example.sushitestapp.utils.Constants.SELECTED_ITEM_ID_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCarFragment : Fragment() {

    private var _binding: EditCarFragmentBinding? = null
    private val binding get() = _binding!!

    private var selectedId: Long = -1

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
        selectedId = arguments?.getLong(SELECTED_ITEM_ID_KEY) ?: NON_CAR_SELECTED_ID
        viewModel.handleEvent(EditCarEvent.GetSelectedCar(selectedId))
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

            viewState.car?.let { car ->
                binding.tilImageUrl.editText?.setText(car.image)
                binding.tilBrand.editText?.setText(car.brand)
                binding.tilModel.editText?.setText(car.model)
                binding.tilColor.editText?.setText(car.color)
                binding.tilYear.editText?.setText(car.year.toString())
                binding.tilMiles.editText?.setText(car.mileage.toString())
                binding.tilVin.editText?.setText(car.vin)
                binding.tilPrice.editText?.setText(car.price.toString())
            }

            if (viewState.isChangesSaved) {
                findNavController().navigateUp()
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
        viewModel.handleEvent(EditCarEvent.SaveChanges(car, selectedId))
    }
}