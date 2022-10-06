package Vihanga.cousework

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

@SuppressLint("SetTextI18n")
class ResultView : AppCompatActivity() {
    var correctAns = 0
    var wrongAns = 0

    private lateinit var txtViewCorrect: TextView
    private lateinit var txtViewWrong: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_result_view)


        txtViewCorrect = findViewById(R.id.correctAns)
        txtViewWrong = findViewById(R.id.wrongAns)

        correctAns = intent.getIntExtra("Correct", 0)
        wrongAns = intent.getIntExtra("Wrong", 0)

        txtViewCorrect.text = "Correct Answers: $correctAns"
        txtViewCorrect.setTextColor(Color.GREEN)
        txtViewWrong.text = "Wrong Answers: $wrongAns"
        txtViewWrong.setTextColor(Color.RED)
    }
}