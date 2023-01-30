package com.project.smolentsev.idleclick.presentation.viewmodel


import android.app.Dialog
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.smolentsev.idleclick.R
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.ClearProgress
import com.project.smolentsev.idleclick.domain.MoneyLogic
import com.project.smolentsev.idleclick.domain.SwitchCharacter
import com.project.smolentsev.idleclick.domain.SwitchHouse
import com.project.smolentsev.idleclick.domain.entity.ResCharacter
import com.project.smolentsev.idleclick.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel

class PlayViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    private var playerMoney: MediaPlayer? = null
    private var playerTap: MediaPlayer? = null
    var dialog: Dialog? = null
    private val _money = MutableLiveData<ResCharacter>()
    private val _relax = MutableLiveData<ResCharacter>()
    private val _hunger = MutableLiveData<ResCharacter>()
    private var _char = MutableLiveData<Int>()
    private var _house = MutableLiveData<Int>()
    private var _earningsPerSecond = MutableLiveData<Long>()
    private lateinit var hungerBalance: Job
    private lateinit var relaxBalance: Job
    var moneyLogic = MoneyLogic(useCases)

    val char: LiveData<Int>
        get() = _char

    val earningsPerSecond: LiveData<Long>
        get() = _earningsPerSecond

    val house: LiveData<Int>
        get() = _house

    val money: LiveData<ResCharacter>
        get() = _money

    val relax: LiveData<ResCharacter>
        get() = _relax

    val hunger: LiveData<ResCharacter>
        get() = _hunger

    fun switchChar() {
        viewModelScope.launch {
            _char.value = SwitchCharacter.getCharacter(useCases.getItemUseCase(const.CHAR).level)
            Log.d("char_lvl", useCases.getItemUseCase(const.CHAR).level.toString())
        }
    }

    fun clickMoneySound(context: Context) {
        playerMoney?.release()
        Log.d("player_destroy", playerMoney?.release().toString())
        playerMoney = MediaPlayer.create(context, R.raw.buy)
        playerMoney?.start()
    }

    fun clickTapSound(context: Context) {
        playerTap?.release()
        playerTap = MediaPlayer.create(context, R.raw.click_aracde)
        playerTap?.start()
    }

    fun destroyPlayer() {
        playerMoney?.release()
        playerTap?.release()
    }

    fun switchHouse() {
        viewModelScope.launch {
            _house.value = SwitchHouse.getHouse(useCases.getItemUseCase(const.HOUSE).level)
        }
    }

    fun moneyPerSec() {
        viewModelScope.launch {
            val _money = moneyLogic.getMoneyPerSec()
            val money = ResCharacter(const.MONEY, "money", _money)
            useCases.editCharUseCase(money)
        }
    }

    fun setResourceText(id: Int) {
        viewModelScope.launch {
            val res = useCases.getCharUseCase(id)
            when (id) {
                const.MONEY -> _money.value = res
                const.RELAX -> _relax.value = res
                const.HUNGER -> _hunger.value = res
            }

        }
    }

    fun clickMoneyButton() {
        viewModelScope.launch {
            val _money = useCases.getCharUseCase(const.MONEY).value + moneyLogic.getIncMoneyCoief()
            val money = ResCharacter(const.MONEY, "money", _money)
            useCases.editCharUseCase(money)
            Log.d("Money_click_PlayVM", moneyLogic.getIncMoneyCoief().toString())
        }
    }

    fun saveLastTime() {
        viewModelScope.launch {
            val lastTime = ResCharacter(const.LAST_TIME, "time", System.currentTimeMillis())
            useCases.editCharUseCase(lastTime)
            Log.d("saveLastTime", useCases.getCharUseCase(const.LAST_TIME).toString())
        }
    }


    fun prettyCount(number: Number): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(numValue / Math.pow(10.0,
                (base * 3).toDouble())) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

    fun relaxBalance() {
       relaxBalance = viewModelScope.launch() {
            if (useCases.getCharUseCase(const.LAST_TIME).value == 0L) {
                val _relaxValue = useCases.getCharUseCase(const.RELAX).value
                val relaxValue = ResCharacter(const.RELAX, "relax", _relaxValue)
                useCases.editCharUseCase(relaxValue)
            } else {
                val lastTime = useCases.getCharUseCase(const.LAST_TIME).value
                val time = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - lastTime)
                val _relaxValue = useCases.getCharUseCase(const.RELAX).value - (time * 2)
                val relaxValue = ResCharacter(const.RELAX, "relax", _relaxValue)
                useCases.editCharUseCase(relaxValue)
            }
            if (useCases.getCharUseCase(const.RELAX).value <= 0L) {
                val relaxValue = ResCharacter(const.RELAX, "relax", 0)
                useCases.editCharUseCase(relaxValue)
            }
        }
    }

    fun hungerBalance() {
       hungerBalance =  viewModelScope.launch {
            if (useCases.getCharUseCase(const.LAST_TIME).value == 0L) {
                val _hungerValue = useCases.getCharUseCase(const.HUNGER).value
                val hungerValue = ResCharacter(const.HUNGER, "hunger", _hungerValue)
                useCases.editCharUseCase(hungerValue)
            } else {
                val lastTime = useCases.getCharUseCase(const.LAST_TIME).value
                val time = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - lastTime)
                val _hungerValue = useCases.getCharUseCase(const.HUNGER).value - (time * 2)
                val hungerValue = ResCharacter(const.HUNGER, "hunger", _hungerValue)
                useCases.editCharUseCase(hungerValue)
            }
            if (useCases.getCharUseCase(const.HUNGER).value <= 0L) {
                val hungerValue = ResCharacter(const.HUNGER, "hunger", 0)
                useCases.editCharUseCase(hungerValue)
            }
        }
    }

    private fun clearProgress() {
        viewModelScope.launch {
            for (item in ClearProgress.initProgressData())
                useCases.editItemUseCase(item)
            for (resource in ClearProgress.initProgressChar())
                useCases.editCharUseCase(resource)
        }
    }


    fun dialogEndGame(context: Context)= runBlocking {
        viewModelScope.launch {
            relaxBalance.join()
            hungerBalance.join()
            if(useCases.getCharUseCase(const.HUNGER).value <= 0L ||
                useCases.getCharUseCase(const.RELAX).value <= 0L) {
                dialog = Dialog(context)
                dialog?.setContentView(R.layout.cancel_dialog)
                dialog?.setCancelable(false)
                val cancel_del: ImageButton? = dialog?.findViewById(R.id.btn_cancel)
                cancel_del?.setOnClickListener {
                    clearProgress()
                    dialog?.dismiss()
                }
                dialog?.show()
            }
        }
    }


    fun moneyUpdate() {
        viewModelScope.launch {
            if (useCases.getCharUseCase(const.LAST_TIME).value == 0L ||
                (moneyLogic.getMoneyPerSec() == 0L)
            ) {
                val _money = useCases.getCharUseCase(const.MONEY).value
                val money = ResCharacter(const.MONEY, "money", _money)
                useCases.editCharUseCase(money)
            } else {
                val lastTime = useCases.getCharUseCase(const.LAST_TIME).value
                val diffSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - lastTime)
                val _money = useCases.getCharUseCase(const.MONEY).value +
                        +(diffSecond * moneyLogic.getMoneyLastTime())
                val money = ResCharacter(const.MONEY, "money", _money)
                useCases.editCharUseCase(money)
            }
        }
    }

    fun earningsPerSecond() {
        viewModelScope.launch {
            _earningsPerSecond.value = moneyLogic.getMoneyLastTime()
        }
    }
}