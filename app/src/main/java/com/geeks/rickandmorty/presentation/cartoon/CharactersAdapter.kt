package com.geeks.rickandmorty.presentation.cartoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.databinding.ItemCharacterBinding
import com.geeks.rickandmorty.domain.models.Character

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.VH>() {

    private val items = mutableListOf<Character>()

    fun setItems(list: List<Character>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val b: ItemCharacterBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: Character) {
            b.characterName.text = item.name
            b.characterStatus.text = item.status
            b.characterSpecies.text = item.species

            Glide.with(b.characterImage.context)
                .load(item.image)
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_menu_report_image)
                .into(b.characterImage)
        }
    }
}
