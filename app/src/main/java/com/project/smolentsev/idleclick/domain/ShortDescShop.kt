package com.project.smolentsev.idleclick.domain

import android.content.Context
import android.util.Log
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.R

object ShortDescShop {
    var title: String = ""
    fun getShortDescShop(idItem: Int, context: Context, item: List<DataItem>): String {
        var itemList = item[idItem]
        Log.d("moneyCoiefShortDesc", const.MONEYCOEFF.toString())
        when (idItem) {
            const.CLICK -> title = context.getString(R.string.short_getclick, itemList.incomeNextLevel)
            const.CHAR -> title = context.getString(R.string.new_сhr)
            const.HOUSE -> title = context.getString(R.string.new_house)
            const.BUILD -> title = context.getString(R.string.bring, itemList.incomeNextLevel)
            const.SHOPPING -> title = context.getString(R.string.bring, itemList.incomeNextLevel)
            const.STARTUP -> title = context.getString(R.string.bring, itemList.incomeNextLevel)
            const.ROCKET -> title = context.getString(R.string.bring, itemList.incomeNextLevel)
            const.COFFE -> title = context.getString(R.string.hunger, itemList.count)
            const.PIZZA -> title = context.getString(R.string.hunger, itemList.count)
            const.SUSHI -> title = context.getString(R.string.hunger, itemList.count)
            const.REST -> title = context.getString(R.string.hunger, itemList.count)
            const.GAME -> title = context.getString(R.string.relax, itemList.count)
            const.SPORT -> title = context.getString(R.string.relax, itemList.count)
            const.KARAOKE -> title = context.getString(R.string.relax, itemList.count)
            const.AIR -> title = context.getString(R.string.relax, itemList.count)
        }
        return title
    }
}