package com.geeks.rickandmorty.presentation.cartoon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.rickandmorty.databinding.ActivityCartoonBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CartoonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartoonBinding
    private lateinit var vm: CartoonViewModel
    private val adapter = CharactersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCartoonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val sys = insets.getInsets(android.view.WindowInsets.Type.systemBars())
            v.setPadding(sys.left, sys.top, sys.right, sys.bottom)
            insets
        }

        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.charactersRecyclerView.adapter = adapter

        val factory = CartoonViewModelFactory()
        vm = ViewModelProvider(this, factory).get(CartoonViewModel::class.java)

        lifecycleScope.launch {
            vm.charactersState.collectLatest { list ->
                adapter.setItems(list)
            }
        }

        vm.loadCharacters()
    }
}
