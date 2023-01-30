package com.project.smolentsev.idleclick.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import com.project.smolentsev.idleclick.presentation.viewmodel.PlayViewModel
import com.project.smolentsev.idleclick.R
import com.project.smolentsev.idleclick.data.const
import com.project.smolentsev.idleclick.domain.checkFirstRun
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayActivity : AppCompatActivity() {
    private val viewModel: PlayViewModel by viewModels()
    private lateinit var textMoney: TextView
    private lateinit var earningsMoney: TextView
    private lateinit var relax: TextView
    private lateinit var hunger: TextView
    private lateinit var clickMoney: ImageButton
    private lateinit var shopBtn: ImageButton
    private lateinit var character: ImageView
    private lateinit var house: ImageView
    private var scale_button: Animation? = null
    private var coin_animation: Animation? = null
    private val checkFirstRun = checkFirstRun()
    private var mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        updateMoneyBackground()
        init()
        Log.d("act_onCreate", "onCreate")
        coin_animation = AnimationUtils.loadAnimation(this, R.anim.rotation_animation)
        scale_button = AnimationUtils.loadAnimation(this, R.anim.scale_button)
        checkFirstRun.checkFirstRun(this)
        setInitParametr()
        clickMoneyBtn()
        clickShopBtn()

    }

    private fun clickShopBtn() {
        shopBtn.setOnClickListener(View.OnClickListener {
            viewModel.clickTapSound(this)
            shopBtn.startAnimation(scale_button)
            val shop = Intent(this, Shop::class.java)
            startActivity(shop)
        })
    }

    private val updateMoneyPerSec = object : Runnable {
        override fun run() {
            viewModel.moneyPerSec()
            setResource()
            mainHandler.postDelayed(this, 1000)
        }
    }

    private fun clickMoneyBtn(){
        clickMoney.setOnClickListener(View.OnClickListener {
            clickMoney.startAnimation(coin_animation)
            viewModel.clickMoneyButton()
            Log.d("MoneyClick_playActivity", viewModel.setResourceText(const.MONEY).toString())
            viewModel.clickMoneySound(this)
        })
    }

    private fun setInitParametr() {
        setResource()
        setChar()
        setHouse()
    }

    private fun init() {
        textMoney = findViewById(R.id.coins_number_textView)
        relax = findViewById(R.id.relax)
        hunger = findViewById(R.id.hung)
        clickMoney = findViewById(R.id.coin_click_button)
        character = findViewById(R.id.chr_1)
        house = findViewById(R.id.house)
        shopBtn = findViewById(R.id.btn_shop)
        earningsMoney = findViewById(R.id.earnings_money_per_sec)

    }

    private fun updateMoneyBackground(){
        viewModel.moneyUpdate()
    }

    private fun setResource() {
        viewModel.setResourceText(const.MONEY)
        viewModel.money.observe(this) {
            textMoney.text = getString(R.string.coins, viewModel.prettyCount(it.value))
        }
        viewModel.setResourceText(const.RELAX)
        viewModel.relax.observe(this) {
            relax.text = it.value.toString()
        }
        viewModel.setResourceText(const.HUNGER)
        viewModel.hunger.observe(this) {
            hunger.text = it.value.toString()
        }
        viewModel.earningsPerSecond()
        viewModel.earningsPerSecond.observe(this) {
            earningsMoney.text = it.toString() + " $/сек"
        }
    }

    private fun setChar() {
        viewModel.switchChar()
        viewModel.char.observe(this){
            character.setImageResource(it)
        }
    }

    private fun setHouse() {
        viewModel.switchHouse()
        viewModel.house.observe(this) {
            house.setImageResource(it)
        }
    }


    override fun onPause() {
        super.onPause()
        viewModel.saveLastTime()
        mainHandler.removeCallbacks(updateMoneyPerSec)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroyPlayer()
    }

    override fun onResume() {
        Log.d("act_onResume", "onResume")
        super.onResume()
        viewModel.hungerBalance()
        viewModel.relaxBalance()
        viewModel.dialogEndGame(this)
        updateMoneyBackground()
        setInitParametr()
        mainHandler.postDelayed(updateMoneyPerSec,1000)

    }
}
