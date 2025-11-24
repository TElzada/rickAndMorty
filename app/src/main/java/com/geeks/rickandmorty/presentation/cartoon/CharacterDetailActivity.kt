package com.geeks.rickandmorty.presentation.cartoon

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.databinding.ActivityCharacterDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHARACTER_ID = "extra_character_id"
    }

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var vm: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_CHARACTER_ID, -1)
        if (id == -1) {
            finish()
            return
        }

        val factory = CharacterDetailViewModelFactory()
        vm = ViewModelProvider(this, factory)[CharacterDetailViewModel::class.java]

        lifecycleScope.launch {
            vm.characterState.collectLatest { ch ->
                ch?.let { character ->
                    binding.detailProgress.isVisible = false

                    binding.detailName.text = character.name
                    binding.detailStatusSpecies.text =
                        "${character.status} • ${character.species}"
                    binding.detailGender.text = character.gender
                    binding.detailLocation.text = character.location.name
                    binding.detailFirstSeen.text = character.episode.firstOrNull() ?: "-"
                    binding.detailEpisodes.text = "Episodes: ${character.episode.size}"
                    binding.detailId.text = "ID: ${character.id}"

                    Glide.with(binding.detailCharacterImage.context)
                        .load(character.image)
                        .centerCrop()
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(binding.detailCharacterImage)
                }
            }
        }

        lifecycleScope.launch {
            vm.isLoading.collectLatest { isLoading ->
                binding.detailProgress.isVisible = isLoading
            }
        }

        lifecycleScope.launch {
            vm.error.collectLatest { error ->
                error?.let {
                    Toast.makeText(this@CharacterDetailActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        vm.loadCharacter(id)
    }
}
