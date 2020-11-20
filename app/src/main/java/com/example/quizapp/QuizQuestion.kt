package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestion : AppCompatActivity() , View.OnClickListener {
    private var mcurrPosition : Int = 1
    private var mQuestionList : ArrayList<Questions>? = null
    private var mselectedOption : Int = 1

    private fun setQues(){
        setDefaultBorder()
        mQuestionList = Constants.getQuestions()
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

        setQues()
        tv_option1.setOnClickListener(this)
        tv_option2.setOnClickListener(this)
        tv_option3.setOnClickListener(this)
        tv_option4.setOnClickListener(this)


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
        }
    }
}