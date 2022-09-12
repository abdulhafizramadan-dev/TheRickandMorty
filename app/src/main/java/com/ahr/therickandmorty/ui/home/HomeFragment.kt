package com.ahr.therickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.ahr.therickandmorty.R
import com.ahr.therickandmorty.databinding.FragmentHomeBinding
import com.ahr.therickandmorty.domain.entity.TabItemCharacter
import com.ahr.therickandmorty.ui.character.CharacterFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment(), TabLayout.OnTabSelectedListener {

    companion object {
        private const val SELECTED_TAB_POSITION = "active_tab_position"
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
        observeTabLayout()

        if (savedInstanceState == null) {
            initialSetup()
        } else {
            val selectedTabPosition = savedInstanceState.getInt(SELECTED_TAB_POSITION)
            binding.tabLayout
            val tab = binding.tabLayout.getTabAt(selectedTabPosition)
            tab?.select()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val selectedTabPosition = binding.tabLayout.selectedTabPosition
        outState.putInt(SELECTED_TAB_POSITION, selectedTabPosition)
    }

    private fun initialSetup() {
        val bundles = bundleOf(
            CharacterFragment.EXTRA_IS_FOR_YOU_TYPE to true,
            CharacterFragment.EXTRA_TAB_ITEM_CHARACTER to getSpecies()[0]
        )
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterFragment::class.java,
                bundles
            )
        }
    }

    private fun setupTabLayout() {
        val titles = getSpecies()
        titles.forEach {
            val tab = binding.tabLayout.newTab().apply {
                text = it.title
                contentDescription = it.title
            }
            binding.tabLayout.addTab(tab)
        }
    }

    private fun observeTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(this)
    }

    private fun getSpecies(): List<TabItemCharacter> {
        return resources.getStringArray(R.array.species).mapIndexed { index, species ->
            TabItemCharacter(
                title = if (index != 0) getString(R.string.format_tab_item, species) else species,
                species = species
            )
        }.toList()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val position = binding.tabLayout.selectedTabPosition
        val isForYouType = position == 0
        val bundles = bundleOf(
            CharacterFragment.EXTRA_IS_FOR_YOU_TYPE to isForYouType,
            CharacterFragment.EXTRA_TAB_ITEM_CHARACTER to getSpecies()[position]
        )
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterFragment::class.java,
                bundles,
            )
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}