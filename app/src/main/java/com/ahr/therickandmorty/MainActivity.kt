package com.ahr.therickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahr.therickandmorty.databinding.ActivityMainBinding
import com.ahr.therickandmorty.helper.FakeData
import com.ahr.therickandmorty.ui.home.HomeEpoxyController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeEpoxyController = HomeEpoxyController()
        binding.epoxyRecyclerView.setController(homeEpoxyController)

        homeEpoxyController.setData(FakeData.getData())
    }
}