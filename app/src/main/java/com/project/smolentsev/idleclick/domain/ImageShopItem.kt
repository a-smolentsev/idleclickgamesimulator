package com.project.smolentsev.idleclick.domain

import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.R

object ImageShopItem {
    var image: Int? = null
    fun getimageShop(idItem: Int): Int? {
        when (idItem) {
            const.CLICK -> image = R.drawable.click
            const.CHAR -> image = R.drawable.upgrade_chr
            const.HOUSE -> image = R.drawable.upgrade_house
            const.BUILD -> image = R.drawable.build
            const.SHOPPING -> image = R.drawable.shop
            const.STARTUP -> image = R.drawable.startup
            const.ROCKET -> image = R.drawable.rocket
            const.COFFE -> image = R.drawable.coffee
            const.PIZZA -> image = R.drawable.pizza
            const.SUSHI -> image = R.drawable.sushi
            const.REST -> image = R.drawable.rest
            const.GAME -> image = R.drawable.game
            const.SPORT -> image = R.drawable.sport
            const.KARAOKE -> image = R.drawable.karaoke
            const.AIR -> image = R.drawable.air
        }
        return image
    }
}