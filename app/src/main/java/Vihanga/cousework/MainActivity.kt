package Vihanga.cousework

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val abtbtn = findViewById<Button>(R.id.aboutbtn)
        val gamebtn = findViewById<Button>(R.id.gamebtn)
        val popupwin = Dialog(this)


        abtbtn.setOnClickListener {
            popupwin!!.setContentView(R.layout.popup)
            popupwin!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popupwin!!.show()
        }

        gamebtn.setOnClickListener {
            val game = Intent(this, GameActivity::class.java)
            startActivity(game)
        }
    }
}