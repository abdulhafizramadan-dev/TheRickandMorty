package com.ahr.therickandmorty.ui.foryou

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ahr.therickandmorty.databinding.FragmentForYouBinding
import com.ahr.therickandmorty.domain.Response

class ForYouFragment : Fragment() {

    companion object {
        private const val TAG = "ForYouFragment"
    }

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!

    private val forYouViewModel: ForYouViewModel by activityViewModels()

    private lateinit var forYouEpoxyController: ForYouEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (forYouViewModel.isFirstLoad.value == true) {
            forYouViewModel.getForYouContent()
        }

        setupForYouController()
        observeForYouContent()
    }

    private fun setupForYouController() {
        forYouEpoxyController = ForYouEpoxyController()
        binding.epoxyForYou.setController(forYouEpoxyController)
    }

    private fun observeForYouContent() {
        forYouViewModel.forYouContent.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    Log.d(TAG, "observeForYouContent: Loading")
                }
                is Response.Success -> {
                    forYouEpoxyController.setData(response.data)
                }
                is Response.Error -> {
                    Log.d(TAG, "observeForYouContent: Error=${response.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}