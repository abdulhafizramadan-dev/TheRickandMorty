package com.ahr.therickandmorty.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahr.therickandmorty.databinding.FragmentCharacterBinding
import com.ahr.therickandmorty.domain.Response
import com.ahr.therickandmorty.domain.entity.TabItemCharacter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    companion object {
        const val EXTRA_IS_FOR_YOU_TYPE = "extra_is_for_you_type"
        const val EXTRA_TAB_ITEM_CHARACTER = "extra_tab_item_character"
        const val EXTRA_SPECIES = "extra_species"
        private const val TAG = "CharacterFragment"
    }

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: CharacterViewModel by viewModels()

    private val species: String? by lazy {
        arguments?.getString(EXTRA_SPECIES)
    }

    private lateinit var characterEpoxyController: CharacterEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.getCharacterList(species)
        setupCharacterController()
        observeCharacterList()
    }


    private fun setupCharacterController() {
        characterEpoxyController = CharacterEpoxyController()
        binding.epoxyCharacter.setController(characterEpoxyController)
    }

    private fun observeCharacterList() {
        characterViewModel.characterList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    Log.d(TAG, "observeCharacterList: Loading")
                }
                is Response.Success -> {
                    characterEpoxyController.setData(response.data)
                }
                is Response.Error -> {
                    Log.d(TAG, "observeCharacterList: Error=${response.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}