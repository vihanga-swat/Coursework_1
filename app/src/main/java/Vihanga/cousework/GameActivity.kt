package Vihanga.cousework

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView

@SuppressLint("SetTextI18n")
class GameActivity : AppCompatActivity() {
    private lateinit var txtView1: TextView
    private lateinit var txtView2: TextView
    private lateinit var txtView3: TextView
    private lateinit var txtView4: TextView
    private lateinit var greaterBtn: Button
    private lateinit var equalBtn: Button
    private lateinit var lessBtn: Button
    private lateinit var cdTimer: CountDownTimer
    private var time = 50

    private val operators = listOf("+", "-", "x", "/")

    private var correctCount = 0
    private var wrongCount = 0
    private var tempCorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_game)

        timerView() //starting the timer
        displaySums()
    }

    private fun displaySums() { //create,check, and display expressions
        var expression1: Pair<Int, String>
        var expression2: Pair<Int, String>
        do {
            expression1 = createSum()
        } while (expression1.first <0 || expression1.first > 100)
        do {
            expression2 = createSum()
        } while (expression2.first <0 || expression2.first > 100)

        var expTxt1 = expression1.second
        var expAns1 = expression1.first
        var expTxt2 = expression2.second
        var expAns2 = expression2.first

        txtView1 = findViewById(R.id.textView1)
        txtView2 = findViewById(R.id.textView2)
        txtView1.text = expTxt1
        txtView2.text = expTxt2

        txtView3 = findViewById(R.id.textView)
        greaterBtn = findViewById(R.id.btn1)
        equalBtn = findViewById(R.id.btn2)
        lessBtn = findViewById(R.id.btn3)

        //https://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay-in-android
        //used to set a delay before displaying a new expression
        fun delay(){
            Handler(Looper.getMainLooper()).postDelayed({
                displaySums()
                txtView3.text = ""
            }, 2000)
        }

        when { //checking and displaying if the user has entered the correct or the wrong answer
            expAns1 > expAns2 -> {
                greaterBtn.setOnClickListener {
                    txtView3.text = "CORRECT"
                    txtView3.setTextColor(Color.GREEN)
                    correctCount++
                    tempCorrect++
                    delay()
                }
                equalBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
                lessBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
            }
            expAns1 == expAns2 -> {
                greaterBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
                equalBtn.setOnClickListener {
                    txtView3.text = "CORRECT"
                    txtView3.setTextColor(Color.GREEN)
                    correctCount++
                    tempCorrect++
                    delay()
                }
                lessBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
            }
            expAns1 < expAns2 -> {
                greaterBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
                equalBtn.setOnClickListener {
                    txtView3.text = "WRONG"
                    txtView3.setTextColor(Color.RED)
                    wrongCount++
                    delay()
                }
                lessBtn.setOnClickListener {
                    txtView3.text = "CORRECT"
                    txtView3.setTextColor(Color.GREEN)
                    correctCount++
                    tempCorrect++
                    delay()
                }
            }
        }
    }

    private fun oneTermSum(): Pair<Int, String> { //displaying one term expression
        var num1 = (1..20).random()
        return Pair(num1, "$num1")
    }

    private fun twoTermSum(): Pair<Int, String>{ //displaying two terms expression
        var num1 = (1..20).random()
        var num2 = (1..20).random()
        var opr = (0..3).random()
        var oprSign = operators[opr]

        var answer: Int = when (opr) {
            0 -> {
                (num1 + num2)
            }
            1 -> {
                (num1 - num2)
            }
            2 -> {
                (num1 * num2)
            }
            else -> {
                (num1 / factorsOfNumber(num1).random())
            }
        }
        return Pair(answer, "$num1 $oprSign $num2")
    }

    private fun threeTermSum(): Pair<Int, String>{ //displaying three terms expression
        var num1 = (1..20).random()
        var num2 = (1..20).random()
        var num3 = (1..20).random()
        var opr1 = (0..3).random()
        var opr2 = (0..3).random()
        var oprSign1 = operators[opr1]
        var oprSign2 = operators[opr2]

        var answer1: Int = when (opr1) {
            0 -> {
                (num1 + num2)
            }
            1 -> {
                (num1 - num2)
            }
            2 -> {
                (num1 * num2)
            }
            else -> {
                (num1 / factorsOfNumber(num1).random())
            }
        }

        var answer2: Int = when (opr2) {
            0 -> {
                (answer1 + num3)
            }
            1 -> {
                (answer1 - num3)
            }
            2 -> {
                (answer1 * num3)
            }
            else -> {
                (answer1 / factorsOfNumber(answer1).random())
            }
        }
        return Pair(answer2, "($num1 $oprSign1 $num2) $oprSign2 $num3")
    }

    private fun fourTermSum(): Pair<Int, String>{ //displaying four terms expression
        var num1 = (1..20).random()
        var num2 = (1..20).random()
        var num3 = (1..20).random()
        var num4 = (1..20).random()
        var opr1 = (0..3).random()
        var opr2 = (0..3).random()
        var opr3 = (0..3).random()
        var oprSign1 = operators[opr1]
        var oprSign2 = operators[opr2]
        var oprSign3 = operators[opr2]

        var answer1: Int = when (opr1) {
            0 -> {
                (num1 + num2)
            }
            1 -> {
                (num1 - num2)
            }
            2 -> {
                (num1 * num2)
            }
            else -> {
                (num1 / factorsOfNumber(num1).random())
            }
        }

        var answer2: Int = when (opr2) {
            0 -> {
                (answer1 + num3)
            }
            1 -> {
                (answer1 - num3)
            }
            2 -> {
                (answer1 * num3)
            }
            else -> {
                (answer1 / factorsOfNumber(answer1).random())
            }
        }

        var answer3: Int = when {
            opr3 == 0 -> {
                (answer2 + num4)
            }
            opr2 == 1 -> {
                (answer2 - num4)
            }
            opr2 == 2 -> {
                (answer2 * num4)
            }
            else -> {
                (answer2 / factorsOfNumber(answer2).random())
            }
        }
        return Pair(answer3, "(($num1 $oprSign1 $num2) $oprSign2 $num3) $oprSign3 $num4")
    }

    private fun createSum(): Pair<Int, String> { //creating a random number between 1-4 so the app chooses how many terms there should be in the expression
        var termsNum = (1..4).random()

        var list: Pair<Int, String> = when (termsNum) {
            1 -> {
                oneTermSum()
            }
            2 -> {
                twoTermSum()
            }
            3 -> {
                threeTermSum()
            }
            else -> {
                fourTermSum()
            }
        }
        return list
    }

    private fun timerView(){ //creating the timer
        txtView4 = findViewById(R.id.timerView)

        cdTimer = object: CountDownTimer(5000000, 1000) {
            override fun onTick(millisUntilFinished: Long) { //set and display the countdown timer
                time--
                txtView4.text = time.toString()
                if (tempCorrect == 5){ //adding 10 seconds to the timer if the user have given 5 correct answers
                    time += 10
                    tempCorrect = 0
                }
                if (time == 0){
                    activity()
                    cdTimer.cancel()
                }
            }

            override fun onFinish() {
                cdTimer.cancel()
            }
        }
        cdTimer.start()
    }

    fun activity(){ //set the values to pass in to the AnswersView
        val answersView = Intent(this, ResultView::class.java)
        answersView.putExtra("Correct", correctCount)
        answersView.putExtra("Wrong", wrongCount)
        startActivity(answersView)
        finish()
    }

    //https://stackoverflow.com/questions/47030439/get-factors-of-numbers-in-kotlin
    //searches for the factors of the previous number and divides by a randomly chosen factor
    private fun factorsOfNumber(num: Int) : MutableList<Int> {
        val factors = mutableListOf<Int>()
        if (num < 1)
            return factors
        (1..num / 2)
            .filter { num % it == 0 }
            .forEach { factors.add(it) }
        factors.add(num)
        return factors
    }

}

