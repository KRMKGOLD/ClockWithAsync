package com.example.undefinedhomework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var mainConstraintSet: ConstraintSet
    private lateinit var timeThread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainConstraintSet = ConstraintSet()
        mainConstraintSet.clone(mainLayout)

        timeThread = Thread(Runnable {
            while (true) {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)

                Handler(Looper.getMainLooper()).post {
                    mainConstraintSet.constrainCircle(R.id.hourTextView, R.id.textView, 210, (hour * 30).toFloat())
                    mainConstraintSet.constrainCircle(R.id.minuteTextView, R.id.textView, 140, (minute * 6).toFloat())
                    mainConstraintSet.constrainCircle(R.id.secondTextView, R.id.textView, 70, (second * 6).toFloat())
                    mainConstraintSet.applyTo(mainLayout)
                    hourTextView.text = hour.toString()
                    minuteTextView.text = minute.toString()
                    secondTextView.text = second.toString()
                }

                try {
                    Thread.sleep(1000)
                } catch (e : Exception) { }
            }
        })

        timeThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timeThread.interrupt()
    }
}
