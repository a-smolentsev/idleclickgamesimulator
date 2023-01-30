package com.project.smolentsev.idleclick.presentation

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.project.smolentsev.idleclick.presentation.adapter.ItemAdapterRV
import com.project.smolentsev.idleclick.presentation.viewmodel.ShopViewModel
import com.project.smolentsev.idleclick.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Shop : AppCompatActivity() {
    private val viewModel: ShopViewModel by viewModels()
    private lateinit var adapterRV: ItemAdapterRV
    private var scale_button: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        scale_button = AnimationUtils.loadAnimation(this, R.anim.scale_button)
        viewModel.itemList.observe(this) {
            adapterRV.itemList = it
            Log.d("itemList_ShopActivity", it.toString())
        }
        setupRecycleView()
    }

    private fun setupRecycleView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewShop)
        adapterRV = ItemAdapterRV(this)
        recyclerView.adapter = adapterRV
        clickItemListener()
    }

    private fun clickItemListener() {
        adapterRV.onShopItemClickListener = {
            viewModel.clickItem(it, this)
            Log.d("Shop_activity", it.toString())
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.destroyPlayer()

    }

}