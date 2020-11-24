package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result_.*

class Result_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility  = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_result_)
        val name = intent.getStringExtra(Constants.name_user)
        val cq : Int  = intent.getIntExtra(Constants.correctans,0)
        val tq  : Int= intent.getIntExtra(Constants.totalques,10)
        tv_name.text = name
        val ans = "You scored " + cq + " out of "+ tq
        tv_name1.text = ans
        finishbtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}