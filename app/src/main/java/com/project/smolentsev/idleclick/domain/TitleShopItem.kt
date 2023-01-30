package com.project.smolentsev.idleclick.domain

import android.content.Context
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.R

object TitleShopItem {
    var title: String = ""
    fun getTitleShop(idItem: Int, context: Context): String {
        when (idItem) {
            const.CLICK -> title = context.getString(R.string.click)
            const.CHAR -> title = context.getString(R.string.character)
            const.HOUSE -> title = context.getString(R.string.house)
            const.BUILD -> title = context.getString(R.string.build)
            const.SHOPPING -> title = context.getString(R.string.shopping)
            const.STARTUP -> title = context.getString(R.string.startup)
            const.ROCKET -> title = context.getString(R.string.rocket)
            const.COFFE -> title = context.getString(R.string.coffe)
            const.PIZZA -> title = context.getString(R.string.pizza)
            const.SUSHI -> title = context.getString(R.string.sushi)
            const.REST -> title = context.getString(R.string.rest)
            const.GAME -> title = context.getString(R.string.game)
            const.SPORT -> title = context.getString(R.string.sport)
            const.KARAOKE -> title = context.getString(R.string.karaoke)
            const.AIR -> title = context.getString(R.string.air)

        }
       return title
    }

}