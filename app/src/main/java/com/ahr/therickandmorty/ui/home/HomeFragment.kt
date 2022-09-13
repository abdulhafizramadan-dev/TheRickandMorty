package com.ahr.therickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.ahr.therickandmorty.R
import com.ahr.therickandmorty.databinding.FragmentHomeBinding
import com.ahr.therickandmorty.ui.character.CharacterFragment
import com.ahr.therickandmorty.ui.foryou.ForYouFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ForYouFragment>(R.id.fragment_container)
        }
    }

    private fun setupTabLayout() {
        val titles = getSpecies()
        titles.forEachIndexed { index, title ->
            val tabTitleFormat = if (index != 0) getString(R.string.format_tab_item, title) else title
            val tab = binding.tabLayout.newTab().apply {
                text = tabTitleFormat
                contentDescription = tabTitleFormat
            }
            binding.tabLayout.addTab(tab)
        }
    }

    private fun observeTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(this)
    }

    private fun getSpecies(): List<String> {
        return resources.getStringArray(R.array.species).toList()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val position = binding.tabLayout.selectedTabPosition
        val isForYouType = position == 0
        val fragment = if (isForYouType) ForYouFragment::class.java else CharacterFragment::class.java
        val species = getSpecies()[position]
        val bundles = bundleOf(CharacterFragment.EXTRA_SPECIES to species)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                fragment,
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