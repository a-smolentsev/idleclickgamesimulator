package com.project.smolentsev.idleclick.domain

import android.util.Log
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.usecase.UseCases
import javax.inject.Inject

class MoneyLogic (private val useCases: UseCases) {
    /** Считает сумму денег, который игрок получает от одного клика **/
    suspend fun getIncMoneyCoief(): Int {
        var coief: Int
        if (useCases.getItemUseCase(const.CLICK).level == 1) {
            coief = 1 + useCases.getItemUseCase(const.CLICK).level

        } else {
            if (useCases.getItemUseCase(const.CLICK).level > 100) {
                coief = 1 + useCases.getItemUseCase(const.CLICK).level * 30
            } else {
                coief = 1 + useCases.getItemUseCase(const.CLICK).level * 5
            }
        }
        return coief
    }

    
    suspend fun getIncMoneyShortDesc(): Int {
        var coief: Int
        if (useCases.getItemUseCase(const.CLICK).level == 1) {
            coief = 1 + useCases.getItemUseCase(const.CLICK).level
        } else {
            if (useCases.getItemUseCase(const.CLICK).level > 100) {
                coief = 1 + (useCases.getItemUseCase(const.CLICK).level+1) * 30
            } else {
                coief = 1 + (useCases.getItemUseCase(const.CLICK).level+1) * 5
            }
        }
        const.MONEYCOEFF=coief
        Log.d("moneyCoief_RepoImpl", const.MONEYCOEFF.toString())
        return coief
    }




    /** Считает сумму денег, который игрок получает, при покупке бизнеса,
     * количество денег заивисит от уровня бизнеса **/
    private suspend fun getMoneyBuild(): Int {
        var money = useCases.getItemUseCase(const.BUILD).level
        return money
    }

    private suspend fun getMoneyShopping(): Int {
        var money = 10 * useCases.getItemUseCase(const.SHOPPING).level
        return money
    }

    private suspend fun getMoneyStartup(): Int {
        var money = 50 * useCases.getItemUseCase(const.STARTUP).level

        return money
    }
    private suspend fun getMoneyRocket(): Int {
        var money = 200 * useCases.getItemUseCase(const.ROCKET).level

        return money
    }

    suspend fun getMoneyLastTime(): Long {
        var moneyPerSec: Long
        if(useCases.getCharUseCase(const.MONEY).value>=useCases.getCharUseCase(const.MAX_MONEY).value) {
            moneyPerSec = 0
        } else{
            if ((useCases.getCharUseCase(const.RELAX).value in 50..100) &&
                (useCases.getCharUseCase(const.HUNGER).value in 50..100)
            ) {
                moneyPerSec = getMoneyBuild() +
                        +getMoneyShopping() +
                        +getMoneyStartup() +
                        +getMoneyRocket().toLong()
            } else if ((useCases.getCharUseCase(const.RELAX).value in 1..49) ||
                (useCases.getCharUseCase(const.HUNGER).value in 1..49)
            ) {
                moneyPerSec = getMoneyBuild() +
                        +(getMoneyShopping() / 2) +
                        +(getMoneyStartup() / 2) +
                        +(getMoneyRocket() / 2).toLong()
            } else moneyPerSec = 0
        }
        return moneyPerSec
    }

    /** Считает сумму денег, который игрок получает в секунду **/
     suspend fun getMoneyPerSec(): Long {
        var moneyPerSec: Long
        if(useCases.getCharUseCase(const.MONEY).value>=useCases.getCharUseCase(const.MAX_MONEY).value) {
            moneyPerSec = useCases.getCharUseCase(const.MONEY).value
        } else {
            if ((useCases.getCharUseCase(const.RELAX).value in 50..100) &&
                (useCases.getCharUseCase(const.HUNGER).value in 50..100)
            ) {
                moneyPerSec = useCases.getCharUseCase(const.MONEY).value +
                        +getMoneyBuild() +
                        +getMoneyShopping() +
                        +getMoneyStartup() +
                        +getMoneyRocket()
            } else if ((useCases.getCharUseCase(const.RELAX).value in 1..49) ||
                (useCases.getCharUseCase(const.HUNGER).value in 1..49)
            ) {
                moneyPerSec = useCases.getCharUseCase(const.MONEY).value +
                        +getMoneyBuild() +
                        +(getMoneyShopping() / 2) +
                        +(getMoneyStartup() / 2) +
                        +(getMoneyRocket() / 2)
            } else moneyPerSec = 0
        }
        return moneyPerSec
    }

}