package com.peggy.discount

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var total = 0
    lateinit var btn_1: Button
    lateinit var btn_5: Button
    lateinit var btn_10: Button
    lateinit var btn_50: Button
    lateinit var btn_100: Button
    lateinit var btn_500: Button
    lateinit var btn_1000: Button
    lateinit var btn_5000: Button
    lateinit var btn_clear: Button
    lateinit var tv_price: TextView
    lateinit var seekbar: SeekBar
    lateinit var tv_offNum: TextView
    lateinit var tv_presentNum: TextView
    lateinit var tv_sale: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        btn_1.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_10.setOnClickListener(this)
        btn_50.setOnClickListener(this)
        btn_100.setOnClickListener(this)
        btn_500.setOnClickListener(this)
        btn_1000.setOnClickListener(this)
        btn_5000.setOnClickListener(this)
        btn_clear.setOnClickListener(this)
        tv_price.setOnClickListener(this)

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (total) {
                    0 -> {
                        seekbar.isEnabled = false
                    }
                    else -> {
                        seekbar.isEnabled = true
                        tv_presentNum.text =
                            "(" + progress.toString() + getString(com.peggy.discount.R.string.percent) + ") " + getString(
                                R.string.colon
                            )
                        tv_sale.text =
                            getString(R.string.money_mark) + (total * progress / 100).toString()
                        tv_offNum.text =
                            getString(R.string.money_mark) + (total - (total * progress / 100)).toString()
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    fun initView() {
        btn_1 = findViewById(R.id.btn_1)
        btn_5 = findViewById(R.id.btn_5)
        btn_10 = findViewById(R.id.btn_10)
        btn_50 = findViewById(R.id.btn_50)
        btn_100 = findViewById(R.id.btn_100)
        btn_500 = findViewById(R.id.btn_500)
        btn_1000 = findViewById(R.id.btn_1000)
        btn_5000 = findViewById(R.id.btn_5000)
        btn_clear = findViewById(R.id.btn_clear)
        tv_price = findViewById(R.id.tv_price)
        seekbar = findViewById(R.id.seekbar)
        tv_offNum = findViewById(R.id.tv_offNum)
        tv_presentNum = findViewById(R.id.tv_presentNum)
        tv_sale = findViewById(R.id.tv_sale)
    }

    @SuppressLint("SetTextI18n")
    fun inputPrice() {
        val mDialogview = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val builder = AlertDialog.Builder(this)
            .setView(mDialogview)
            .setTitle(R.string.input_coin)

        builder.setPositiveButton(R.string.sure) { dialog, which ->

            var input = mDialogview.findViewById<EditText>(R.id.et_input).text.toString()
            if (input == "") {
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 120)
                toast.view = layoutInflater.inflate(R.layout.custom_toast_money, null)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
            } else {
                total = input.toInt()
                tv_price.text = getString(R.string.money_mark) + input
                seekbar.progress = 0
                tv_offNum.text = getString(R.string.money_mark) + total.toString()
                TextColorBlack()
                btn_clear.visibility = View.VISIBLE
            }
        }
        builder.setNegativeButton(R.string.cancel) { dialog, which ->
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 120)
            toast.view = layoutInflater.inflate(R.layout.custom_toast_cancel, null)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }
        val mAlertDialog = builder.create()
        mAlertDialog.show()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btn_1 -> {
                Animation(1)
            }
            R.id.btn_5 -> {
                Animation(5)
            }

            R.id.btn_10 -> {
                Animation(10)
            }

            R.id.btn_50 -> {
                Animation(50)
            }

            R.id.btn_100 -> {
                Animation(100)
            }

            R.id.btn_500 -> {
                Animation(500)
            }

            R.id.btn_1000 -> {
                Animation(1000)
            }

            R.id.btn_5000 -> {
                Animation(5000)
            }
            R.id.btn_clear -> {
                Animation(0)
            }
            R.id.tv_price -> {
                inputPrice()
            }

        }
    }


    @SuppressLint("SetTextI18n")
    fun Animation(price: Int) {

        var animation: ValueAnimator
        seekbar.isEnabled = true
        if (price != 0) {
            TextColorBlack()
            btn_clear.visibility = View.VISIBLE
            animation = ValueAnimator.ofInt(total, total + price)
        } else {
            btn_clear.visibility = View.INVISIBLE
            animation = ValueAnimator.ofInt(total, 0)
            TextColorGray()
        }
        seekbar.progress = 0
        animation.addUpdateListener {
            total = animation.animatedValue as Int
            tv_price.text = getString(R.string.money_mark) + total
            tv_offNum.text = getString(R.string.money_mark) + total
        }
        animation.duration = 300
        animation.start()
    }

    fun TextColorGray() {
        tv_price.setTextColor(resources.getColor(R.color.text_gray))
        tv_sale.setTextColor(resources.getColor(R.color.text_gray))
        tv_offNum.setTextColor(resources.getColor(R.color.text_gray))
    }

    fun TextColorBlack() {
        tv_price.setTextColor(resources.getColor(R.color.text_black));
        tv_sale.setTextColor(resources.getColor(R.color.text_black));
        tv_offNum.setTextColor(resources.getColor(R.color.text_black));
    }

}