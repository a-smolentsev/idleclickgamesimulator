package com.project.smolentsev.idleclick.presentation.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.project.smolentsev.idleclick.R

class MainViewModel: ViewModel() {
    private var playerTap: MediaPlayer? = null

    fun clickBtn(context: Context) {
        playerTap?.release()
        playerTap = MediaPlayer.create(context, R.raw.click_aracde)
        playerTap?.start()
    }

    fun destroyPlayer() {
        playerTap?.release()
    }
}