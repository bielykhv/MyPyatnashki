package com.example.mypyatnashki

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.lang.Math.abs

class OnSwipeTouchListener internal constructor(mainView: View, val activity: MainActivity):View.OnTouchListener{
    private val gestureDetector: GestureDetector
    private lateinit var onSwipe:OnSwipeListener
    init{
        gestureDetector = GestureDetector(activity.baseContext, GestureListener())
        mainView.setOnTouchListener(this)
    }
    override fun onTouch(v:View, event: MotionEvent):Boolean {
        return gestureDetector.onTouchEvent(event)
    }
    private companion object {
        private const val swipeThreshold = 100
        private const val swipeVelocityThreshold = 100
    }
    inner class GestureListener:GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e:MotionEvent):Boolean {
            return true
        }
        override fun onFling(e1:MotionEvent, e2:MotionEvent, velocityX:Float, velocityY:Float):Boolean {
            var result = false
            try{
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (abs(diffX) > abs(diffY)){
                    if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold){
                        if (diffX > 0){
                            onSwipeRight()
                        }
                        else{
                            onSwipeLeft()
                        }
                        result = true
                    }
                }
                else if (abs(diffY) > swipeThreshold && abs(velocityY) > swipeVelocityThreshold){
                    if (diffY > 0){
                        onSwipeBottom()
                    }
                    else{
                        onSwipeTop()
                    }
                    result = true
                }
            }
            catch (exception:Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }
    internal fun onSwipeRight() {
        activity.swipeRight()
        this.onSwipe.swipeRight()
    }
    internal fun onSwipeLeft() {
        activity.swipeLeft()
        this.onSwipe.swipeLeft()
    }
    internal fun onSwipeTop() {
        activity.swipeUp()
        this.onSwipe.swipeTop()
    }
    internal fun onSwipeBottom() {
        activity.swipeDown()
        this.onSwipe.swipeBottom()
    }
    internal interface OnSwipeListener {
        fun swipeRight()
        fun swipeTop()
        fun swipeBottom()
        fun swipeLeft()
    }
}
