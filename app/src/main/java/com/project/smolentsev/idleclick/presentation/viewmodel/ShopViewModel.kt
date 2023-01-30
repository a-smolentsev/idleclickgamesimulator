package com.project.smolentsev.idleclick.presentation.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.smolentsev.idleclick.data.const.Companion.HUNGER
import com.project.smolentsev.idleclick.data.const.Companion.MONEY
import com.project.smolentsev.idleclick.data.const.Companion.RELAX
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_HUNGER
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_MONEY
import com.project.smolentsev.idleclick.data.const.Companion.TEMP_RELAX
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.domain.usecase.UseCases
import com.project.smolentsev.idleclick.R
import com.project.smolentsev.idleclick.domain.MoneyLogic
import com.project.smolentsev.idleclick.domain.OnClickItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ShopViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {
    private var playerMoney: MediaPlayer? = null
    private var playerTap: MediaPlayer? = null
    val itemList = useCases.getItemListUseCase()
    val moneyLogic = MoneyLogic(useCases)

    fun clickItem(dataItem: DataItem, context: Context){
        viewModelScope.launch {
            TEMP_MONEY = useCases.getCharUseCase(MONEY).value
            TEMP_RELAX = useCases.getCharUseCase(RELAX).value
            TEMP_HUNGER = useCases.getCharUseCase(HUNGER).value
            Log.d("TEMP_MONEY_1", TEMP_MONEY.toString())
            if(OnClickItem.whenIdItemShop(dataItem,context)){
                useCases.editItemUseCase(OnClickItem.updateItem())
                useCases.editCharUseCase(OnClickItem.updateEditMoney())
                useCases.editCharUseCase(OnClickItem.updateHunger())
                useCases.editCharUseCase(OnClickItem.updateRelax())
                buySound(context)
            } else clickSound(context)

            moneyLogic.getMoneyPerSec()
            Log.d("TEMP_MONEY_OSTATOK", useCases.getCharUseCase(MONEY).value.toString())
        }
    }

    fun buySound(context:Context) {
        playerMoney?.release()
        playerMoney = MediaPlayer.create(context, R.raw.buy)
        playerMoney?.start()
    }

    fun clickSound(context: Context) {
        playerTap?.release()
        playerTap = MediaPlayer.create(context, R.raw.click_aracde)
        playerTap?.start()
    }

    fun destroyPlayer() {
        playerMoney?.release()
        playerTap?.release()
    }

}
