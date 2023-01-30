package com.project.smolentsev.idleclick.presentation

import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.smolentsev.idleclick.R
import android.content.Intent
import android.view.Menu
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.project.smolentsev.idleclick.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var bg: ImageView? = null
    private var scale_button: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scale_button = AnimationUtils.loadAnimation(applicationContext, R.anim.scale_button)
        initPlayButtonAction()
        playSetting()
        bg = findViewById(R.id.bgmain)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_settings_menu, menu)
        return true
    }

    private fun initPlayButtonAction() {
        val play_button = findViewById<Button>(R.id.play_button)
        play_button.setOnClickListener {
            viewModel.clickBtn(this)
            play_button.startAnimation(scale_button)
            val i = Intent(this@MainActivity, PlayActivity::class.java)
            startActivity(i)
        }
    }

    private fun playSetting() {
        val play_button = findViewById<Button>(R.id.settings)
        play_button.setOnClickListener {
            viewModel.clickBtn(this)
            play_button.startAnimation(scale_button)
            val i = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bg!!.setImageDrawable(null)
        viewModel.destroyPlayer()
    }
}