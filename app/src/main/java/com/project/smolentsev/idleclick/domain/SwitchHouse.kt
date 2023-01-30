package com.project.smolentsev.idleclick.domain

import com.project.smolentsev.idleclick.R

object SwitchHouse {
    var image: Int = 0
    fun getHouse(levelHouse: Int): Int {
            when (levelHouse) {
                0 -> image = R.drawable.h1
                1 -> image = R.drawable.h2
                2 -> image = R.drawable.h3
                3 -> image = R.drawable.h4
                4 -> image = R.drawable.h5
                5 -> image = R.drawable.h6
                6 -> image = R.drawable.h7
                7 -> image = R.drawable.h8
                8 -> image = R.drawable.h9
                9 -> image = R.drawable.h10
                10 -> image = R.drawable.h11
                11 -> image = R.drawable.h12
                12 -> image = R.drawable.h13
                13 -> image = R.drawable.h14
                14 -> image = R.drawable.h15
                15 -> image = R.drawable.h16
                16 -> image = R.drawable.h17
                17 -> image = R.drawable.h18
            }
         return image
        }
}