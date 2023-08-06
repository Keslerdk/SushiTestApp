package com.example.sushitestapp.presentor.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.sushitestapp.databinding.ItemCarCardBinding
import com.example.sushitestapp.presentor.models.CarCard

class CarListAdapter(private val onItemClick: (id: Long) -> Unit) :
    ListAdapter<CarCard, CarListAdapter.Holder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCarCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(
        private val vb: ItemCarCardBinding,
        private val onClick: (id: Long) -> Unit
    ) : ViewHolder(vb.root) {
        fun bind(car: CarCard) {
            vb.sdvPhoto.load(car.image)
            vb.tvColor.text = car.color
            vb.tvName.text = car.name
            vb.tvVin.text = car.vin
            vb.tvStatus.text = car.status
            vb.tvMiles.text = car.miles
            vb.tvPrice.text = car.price
            vb.root.setOnClickListener { onClick.invoke(car.id) }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<CarCard>() {
        override fun areItemsTheSame(oldItem: CarCard, newItem: CarCard): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CarCard, newItem: CarCard): Boolean =
            oldItem.toString() == newItem.toString()

    }
}