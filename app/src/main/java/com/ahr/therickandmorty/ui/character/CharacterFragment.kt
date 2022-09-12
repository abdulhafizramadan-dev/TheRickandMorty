package com.ahr.therickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ahr.therickandmorty.databinding.FragmentCharacterBinding
import com.ahr.therickandmorty.domain.entity.TabItemCharacter
import com.ahr.therickandmorty.helper.FakeData

class CharacterFragment : Fragment() {

    companion object {
        const val EXTRA_IS_FOR_YOU_TYPE = "extra_is_for_you_type"
        const val EXTRA_TAB_ITEM_CHARACTER = "extra_tab_item_character"
    }

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    private val isForYouType: Boolean by lazy {
        arguments?.getBoolean(EXTRA_IS_FOR_YOU_TYPE) ?: false
    }
    private val tabItemCharacter: TabItemCharacter? by lazy {
        arguments?.getParcelable(EXTRA_TAB_ITEM_CHARACTER)
    }

    private lateinit var forYouEpoxyController: ForYouEpoxyController
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

        if (isForYouType) {
            setupForYouController()
        } else {
            setupCharacterController()
        }
    }

    private fun setupForYouController() {
        forYouEpoxyController = ForYouEpoxyController()
        binding.epoxyRecyclerView.setController(forYouEpoxyController)
        forYouEpoxyController.setData(FakeData.getDataForYou())
    }

    private fun setupCharacterController() {
        binding.epoxyRecyclerView.setPadding(0, 8, 0, 8)
        binding.epoxyRecyclerView.clipToPadding = false
        characterEpoxyController = CharacterEpoxyController()
        binding.epoxyRecyclerView.setController(characterEpoxyController)
        characterEpoxyController.setData(tabItemCharacter?.species?.let {
            FakeData.getDataCharacters(
                it
            )
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}