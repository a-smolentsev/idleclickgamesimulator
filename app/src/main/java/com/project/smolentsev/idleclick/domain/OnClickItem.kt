package com.project.smolentsev.idleclick.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_HUNGER
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_MONEY
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_RELAX
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.R
import kotlin.math.pow


object OnClickItem {
    private const val MAX_RELAX = 100
    private const val MAX_HUNGER = 100
    private const val MAX_LEVEL_CHAR = 15
    private const val MAX_LEVEL_HOUSE = 17
    private var editMoney = ResCharacter(const.MONEY,"money", TEMP_MONEY)
    private var editItem = DataItem(100,"",0,0,0,0)
    private var editRelax = ResCharacter(const.RELAX,"relax",TEMP_RELAX)
    private var editHunger = ResCharacter(const.HUNGER,"hunger",TEMP_HUNGER)
    private const val coeficient = 1.12
    private const val coeficientChar = 1.75
    private const val coeficientHouse = 1.92
    private const val basePriceClick = 200
    private const val basePriceChar = 3500
    private const val basePriceHouse = 2500
    private const val basePriceBuilding = 5000
    private const val basePriceShop = 20000
    private const val basePriceStartup = 500000
    private const val basePriceRocket = 3000000

    fun updateEditMoney(): ResCharacter = editMoney
    fun updateItem(): DataItem = editItem
    fun updateHunger(): ResCharacter = editHunger
    fun updateRelax(): ResCharacter = editRelax

    private fun subMoneyPrice(dataItem: DataItem) {
        editMoney = ResCharacter(const.MONEY,
            "money", TEMP_MONEY - dataItem.price)
    }
    private fun isMaxLvlChar(dataItem: DataItem):Boolean {
        return dataItem.level == MAX_LEVEL_CHAR
    }
    private fun isMaxLvlHouse(dataItem: DataItem):Boolean {
        return dataItem.level == MAX_LEVEL_HOUSE
    }
    private fun noMoney(context: Context) {
        val toast = Toast.makeText(context, R.string.not_enough_money, Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun maxLvlToast(context: Context) {
        val toast = Toast.makeText(context, R.string.max_lvl, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun fullHunger(context: Context) {
        val toast = Toast.makeText(context, R.string.full_hunger, Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun fullRelax(context: Context) {
        val toast = Toast.makeText(context, R.string.full_relax, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun addHunger(dataItem: DataItem) {
        val result: Int
        val deltaRes = MAX_HUNGER - TEMP_HUNGER
        Log.d("EDA_HUNGER", TEMP_HUNGER.toString())
        Log.d("EDA_DELTA",deltaRes.toString())
        result = if (dataItem.count > deltaRes) {
            deltaRes.toInt()
        } else dataItem.count
        Log.d("EDA_RESULT",result.toString())
        editHunger = ResCharacter(const.HUNGER,"Hunger",
            TEMP_HUNGER+result)
    }

    private fun addRelax(dataItem: DataItem){
        val result: Int
        val deltaRes = MAX_RELAX - TEMP_RELAX
        result = if (dataItem.count > deltaRes) {
            deltaRes.toInt()
        } else dataItem.count
        editRelax = ResCharacter(const.RELAX,"Relax",
            TEMP_RELAX+result)
    }

    // Доход со следуюшего уровня, который показывается в описании
    private fun incomeNextLvl(dataItem: DataItem): Int {
        var incomeNext = 0
        when (dataItem.id) {
            const.CLICK ->
                incomeNext = if (dataItem.level > 100) {
                    1 + (dataItem.level + 2) * 30
                } else {
                    1 + (dataItem.level + 2) * 5
                }

            const.BUILD -> incomeNext = 2 + dataItem.level
            const.SHOPPING -> incomeNext = 10 * (2 + dataItem.level)
            const.STARTUP -> incomeNext = 50 * (2 + dataItem.level)
            const.ROCKET -> incomeNext = 200 * (2 + dataItem.level)
        }
        return incomeNext
    }

    private fun priceItem(dataItem: DataItem): Long{
        var price = 0L
        if(dataItem.level<2){
            when(dataItem.id) {
                const.CLICK -> price = (basePriceClick * coeficient.pow(dataItem.level)).toLong()
                const.CHAR -> price = (dataItem.price * (coeficientChar-0.12)).toLong()
                const.HOUSE -> price = (dataItem.price* (coeficientHouse-0.15)).toLong()
                const.BUILD -> price = (dataItem.price * (coeficient-0.02)).toLong()
                const.SHOPPING -> price = (dataItem.price* (coeficient-0.02)).toLong()
                const.STARTUP -> price = (dataItem.price * (coeficient-0.02)).toLong()
                const.ROCKET -> price = (dataItem.price* (coeficient-0.02)).toLong()
            }
        }
        else{
            when(dataItem.id) {
                const.CLICK -> price = (basePriceClick * coeficient.pow(dataItem.level)).toLong()
                const.CHAR -> price = (basePriceChar * coeficientChar.pow(dataItem.level)).toLong()
                const.HOUSE -> price = (basePriceHouse * coeficientHouse.pow(dataItem.level)).toLong()
                const.BUILD -> price = (basePriceBuilding * coeficient.pow(dataItem.level)).toLong()
                const.SHOPPING -> price = (basePriceShop * coeficient.pow(dataItem.level)).toLong()
                const.STARTUP -> price = (basePriceStartup * coeficient.pow(dataItem.level)).toLong()
                const.ROCKET -> price = (basePriceRocket * coeficient.pow(dataItem.level)).toLong()
            }


        }
        return  price
    }

    fun whenIdItemShop(dataItem: DataItem, context: Context): Boolean {
        val enoughMoney = TEMP_MONEY >= dataItem.price
        val checkRelax = TEMP_RELAX < MAX_RELAX
        val checkHunger = TEMP_HUNGER < MAX_HUNGER


        var resultEdit = false
        when (dataItem.id) {
            const.CLICK -> if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "money",
                    dataItem.level + 1, priceItem(dataItem),
                    0, incomeNextLvl(dataItem))
                resultEdit = true
            } else noMoney(context)

            const.CHAR -> if (isMaxLvlChar(dataItem))
                maxLvlToast(context)
                else {
                    if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "char",
                    dataItem.level + 1, priceItem(dataItem),
                    0, incomeNextLvl(dataItem))
                resultEdit = true
            } else noMoney(context)}

            const.HOUSE -> if(isMaxLvlHouse(dataItem))
                maxLvlToast(context)
                else {
                    if (enoughMoney) {
                    subMoneyPrice(dataItem)
                    editItem = DataItem(dataItem.id, "house",
                        dataItem.level + 1, priceItem(dataItem),
                        0, incomeNextLvl(dataItem))
                    resultEdit = true

                } else noMoney(context)
            }

            const.BUILD -> if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "build",
                    dataItem.level + 1, priceItem(dataItem) ,
                    0, incomeNextLvl(dataItem))

                resultEdit = true
            } else noMoney(context)

            const.SHOPPING -> if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "shopping",
                    dataItem.level + 1, priceItem(dataItem),
                    0, incomeNextLvl(dataItem))
                resultEdit = true

            } else noMoney(context)

            const.STARTUP -> if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "startup",
                    dataItem.level + 1, priceItem(dataItem),
                    0, incomeNextLvl(dataItem))
                resultEdit = true
            } else noMoney(context)

            const.ROCKET -> if (enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = DataItem(dataItem.id, "rocket",
                    dataItem.level+1, priceItem(dataItem),
                    0, incomeNextLvl(dataItem))
                resultEdit = true

            } else noMoney(context)

            const.COFFE -> if (checkHunger && enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = dataItem
                addHunger(dataItem)
                resultEdit = true
            } else {
                if(!checkHunger)  fullHunger(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.PIZZA -> if (checkHunger && enoughMoney) {
                subMoneyPrice(dataItem)
                editItem = dataItem
                addHunger(dataItem)
                resultEdit = true

            } else {
                if(!checkHunger)  fullHunger(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.SUSHI -> if (checkHunger && enoughMoney) {
                subMoneyPrice(dataItem)
                addHunger(dataItem)
                editItem = dataItem
                resultEdit = true

            } else {
                if(!checkHunger)  fullHunger(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.REST -> if (checkHunger && enoughMoney) {
                subMoneyPrice(dataItem)
                addHunger(dataItem)
                editItem = dataItem
                resultEdit = true
            } else {
                if(!checkHunger)  fullHunger(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.GAME -> if (checkRelax && enoughMoney) {
                subMoneyPrice(dataItem)
                addRelax(dataItem)
                editItem = dataItem
                resultEdit = true
            } else {
                if(!checkHunger)  fullRelax(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.SPORT -> if (checkRelax && enoughMoney) {
                subMoneyPrice(dataItem)
                addRelax(dataItem)
                editItem = dataItem
                resultEdit = true
            } else {
                if(!checkHunger)  fullRelax(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.KARAOKE -> if (checkRelax && enoughMoney) {
                subMoneyPrice(dataItem)
                addRelax(dataItem)
                editItem = dataItem
                resultEdit = true
            } else {
                if(!checkHunger)  fullRelax(context)
                if(!enoughMoney)  noMoney(context)
            }

            const.AIR -> if (checkRelax && enoughMoney) {
                subMoneyPrice(dataItem)
                addRelax(dataItem)
                editItem = dataItem
                resultEdit = true
            } else {
                if(!checkHunger)  fullRelax(context)
                if(!enoughMoney)  noMoney(context)
            }
        }
        return resultEdit
    }

}