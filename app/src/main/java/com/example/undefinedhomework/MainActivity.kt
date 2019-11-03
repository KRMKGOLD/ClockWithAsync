package com.example.undefinedhomework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintSet
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Subscriber
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable
    private lateinit var mainConstraintSet: ConstraintSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainConstraintSet = ConstraintSet()
        mainConstraintSet.clone(mainLayout)

        disposable = Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)

                mainConstraintSet.constrainCircle(R.id.hourTextView, R.id.textView, 210, (hour * 30).toFloat())
                mainConstraintSet.constrainCircle(R.id.minuteTextView, R.id.textView, 140, (minute * 6).toFloat())
                mainConstraintSet.constrainCircle(R.id.secondTextView, R.id.textView, 70, (second * 6).toFloat())
                mainConstraintSet.applyTo(mainLayout)
                hourTextView.text = hour.toString()
                minuteTextView.text = minute.toString()
                secondTextView.text = second.toString()
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
