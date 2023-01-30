package com.project.smolentsev.idleclick.domain

import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.entity.ResCharacter


object ClearProgress {
    fun initProgressData(): MutableList<DataItem> {
        return mutableListOf(DataItem(const.CLICK, "click", 1, 200,
            0, 11),
            DataItem(const.CHAR, "char", 0, 3500,
                0, 0),
            DataItem(const.HOUSE, "house", 0, 2500,
                0, 0),
            DataItem(const.BUILD, "build", 0, 5000,
                0, 2),
            DataItem(const.SHOPPING, "shopping", 0, 20000,
                0, 10),
            DataItem(const.STARTUP, "startup", 0, 500000,
                0, 50),
            DataItem(const.ROCKET, "rocket", 0, 3000000,
                0, 200))
    }
    fun initProgressChar(): MutableList<ResCharacter> {
        return mutableListOf(ResCharacter(const.MONEY, "money", 100000),
            ResCharacter(const.HUNGER, "hunger", 100),
            ResCharacter(const.RELAX, "relax", 100),
            ResCharacter(const.LAST_TIME, "last time", 0),
            ResCharacter(const.MAX_MONEY, "max money", 10000000000000))
    }

}