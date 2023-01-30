package com.project.smolentsev.idleclick.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.smolentsev.idleclick.presentation.viewmodel.SettingsViewModel
import com.project.smolentsev.idleclick.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity: AppCompatActivity() {
    private val viewModel: SettingsViewModel by viewModels()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_delete_progress)
            val delete = findViewById<Button>(R.id.del_progress)
            val about = findViewById<Button>(R.id.url)
            clearProgressBtn(delete)
            aboutBtn(about)
        }

    private fun aboutBtn(about: Button) {
        about.setOnClickListener {
            val url = "https://gamesoft-sm.github.io/idlebusinessman/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun clearProgressBtn(delete: Button) {
               delete.setOnClickListener {
                   viewModel.clearProgress()
                   Toast.makeText(applicationContext,
                       R.string.successfully_deleted,
                       Toast.LENGTH_SHORT).show()
               }
           }


}