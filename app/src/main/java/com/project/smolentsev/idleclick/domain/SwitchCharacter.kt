package com.project.smolentsev.idleclick.domain

import android.util.Log

import com.project.smolentsev.idleclick.R

object SwitchCharacter {
    var image: Int = 0
    fun getCharacter(levelChar: Int): Int {
        when(levelChar)
        {   0 -> image=R.drawable.chr_1
            1 -> image=R.drawable.chr_2
            2 -> image=R.drawable.chr_3
            3 -> image=R.drawable.chr_4
            4 -> image=R.drawable.chr_5
            5 -> image=R.drawable.chr_6
            8 -> image=R.drawable.chr_9
            7 -> image=R.drawable.chr_8
            6 -> image=R.drawable.chr_7
            9 -> image=R.drawable.chr_10
            10 -> image=R.drawable.chr_11
            11 -> image=R.drawable.chr_12
            12 -> image=R.drawable.chr_13
            13 -> image=R.drawable.chr_14
            14 -> image=R.drawable.chr_15
            15 -> image=R.drawable.chr_16
        }
        Log.d("Image", image.toString())
      return image
    }


}