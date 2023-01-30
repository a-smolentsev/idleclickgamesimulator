package com.project.smolentsev.idleclick.domain

object MoneyBackgroundUpd {
    fun moneyUpdate(lastTime: Long, currentTime: Long, moneyPerSec: Long): Long {
        var money = (currentTime-lastTime)*moneyPerSec
        return money
    }
}