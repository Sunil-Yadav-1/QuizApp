package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestion : AppCompatActivity() , View.OnClickListener {
    private var mcurrPosition : Int = 1
    private var mQuestionList : ArrayList<Questions>? = null
    private var mselectedOption : Int = 0
    private var mcorrectAns : Int = 0
    private var mName : String? = null

    private fun setQues(){
        setDefaultBorder()
        if(mcurrPosition == mQuestionList!!.size){
            btnsubmit.text = "FINISH"
        }else{
            btnsubmit.text = "SUBMIT"
        }

        val Que : Questions?= mQuestionList!![mcurrPosition-1]
        tv_text.text = Que!!.question
        iv_image.setImageResource(Que.image)
        tv_option1.text= Que.option1
        tv_option2.text = Que.option2
        tv_option3.text = Que.option3
        tv_option4.text = Que.option4
        progressBar.progress = mcurrPosition
        tv_pbtext.text = "${mcurrPosition}"+"/"+progressBar.max
    }
    private fun setDefaultBorder(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option1)
        options.add(1,tv_option2)
        options.add(2,tv_option3)
        options.add(3,tv_option4)

        for(option in options){
            option.setTextColor(Color.parseColor("#FFFFFF"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
            R.drawable.default_option_border_bg)
        }
    }

    private fun setSelectedBorder(tv:TextView,selectedoption : Int){
        setDefaultBorder()
        mselectedOption = selectedoption
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background= ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        mQuestionList = Constants.getQuestions()
        setQues()
        mName = intent.getStringExtra(Constants.name_user)
        tv_option1.setOnClickListener(this)
        tv_option2.setOnClickListener(this)
        tv_option3.setOnClickListener(this)
        tv_option4.setOnClickListener(this)
        btnsubmit.setOnClickListener(this)

    }

    private fun answerview(answer:Int,drawableview : Int){
        when(answer){
            1 -> {
                tv_option1.background = ContextCompat.getDrawable(this,drawableview)
            }
            2 -> {
                tv_option2.background = ContextCompat.getDrawable(this,drawableview)
            }
            3 -> {
                tv_option3.background = ContextCompat.getDrawable(this,drawableview)
            }
            4 -> {
                tv_option4.background = ContextCompat.getDrawable(this,drawableview)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option1 ->{
                setSelectedBorder(tv_option1,1)
            }
            R.id.tv_option2 ->{
                setSelectedBorder(tv_option2,2)
            }
            R.id.tv_option3 ->{
                setSelectedBorder(tv_option3,3)
            }
            R.id.tv_option4 ->{
                setSelectedBorder(tv_option4,4)
            }
            R.id.btnsubmit ->{
                if(mselectedOption == 0){
                    mcurrPosition ++
                    when{
                        mcurrPosition<= mQuestionList!!.size -> {
                            setQues()
                        }else ->{
                            val intent = Intent(this,Result_Activity::class.java)
                            intent.putExtra(Constants.name_user,mName)
                            intent.putExtra(Constants.correctans, mcorrectAns)
                            intent.putExtra(Constants.totalques,mQuestionList!!.size)
                            startActivity(intent)
                    }
                    }
                }else{
                    val helperque = mQuestionList!![mcurrPosition-1]
                    if(helperque.correctans != mselectedOption){
                        answerview(mselectedOption,R.drawable.wrong_option_border_bg)
                    }else{
                        mcorrectAns++
                    }
                    answerview(helperque.correctans,R.drawable.correct_option_border_bg)
                    if(mcurrPosition == mQuestionList!!.size){
                        btnsubmit.text = "FINISH"
                    }else{
                        btnsubmit.text = "NEXT"
                    }
                    mselectedOption = 0
                }
            }
        }
    }
}