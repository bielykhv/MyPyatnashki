package com.example.mypyatnashki

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mypyatnashki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var onSwipeTouchListener: OnSwipeTouchListener? = null
    private lateinit var binding: ActivityMainBinding
    private val list by lazy {
        mutableListOf<TextView>().apply {
            add(binding.view1)
            add(binding.view2)
            add(binding.view3)
            add(binding.view4)
            add(binding.view5)
            add(binding.view6)
            add(binding.view7)
            add(binding.view8)
            add(binding.view9)
            add(binding.view10)
            add(binding.view11)
            add(binding.view12)
            add(binding.view13)
            add(binding.view14)
            add(binding.view15)
            add(binding.view16)

        }
    }
    private var charList = mutableListOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        ""
    )
    private var winList = mutableListOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        ""
    )
    private var checklist = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startGame()
        onSwipeTouchListener = OnSwipeTouchListener(binding.constLayout, this)
        binding.imageView.setOnClickListener {
            startGame()
        }
        binding.imageView3.setOnClickListener {
            AlertDialog.Builder(this)
//                .setIcon(R.mipmap.ic_launcher)
                .setTitle("How to play")
                .setMessage(
                    "Arrange the numbers from 1 to 15 starting from the" +
                            " top left corner to the bottom right corner. " +
                            "An empty cell should render in the lower right corner.Use swipes"
                )
                .setPositiveButton("Back", null)
                .create()
                .show()
        }

    }

    private fun startGame() {
        charList.shuffle()
        putNum()
        setBackground()
    }

    private fun isEqual(first: List<String>, second: List<String>): Boolean {

        return first.zip(second).all { (x, y) -> x == y }
    }

    private fun getCheckList(): List<String> {
        checklist.clear()
        for (i in 0..15) {
            checklist.add(list[i].text.toString())
        }
        return checklist
    }

    private fun putNum() {
        for (i in 0..15) {
            list[i].text = charList[i]
        }
    }

    private fun setBackground() {
        for (i in 0..15) {
            if (list[i].text == "") {
                list[i].setBackgroundColor(resources.getColor(R.color.violet))
            } else {
                list[i].setBackgroundColor(resources.getColor(R.color.purple_200))
            }
        }
    }

    fun swipeLeft() {
        for (v in 15 downTo 0) {
            if (v != 3 && v != 7 && v != 11 && v != 15) {
                if (list[v].text == "") {
                    list[v].text = list[v + 1].text
                    list[v + 1].text = ""
                }

            }


        }
        setBackground()
        if (isEqual(getCheckList(), winList)) binding.textView.text = getString(R.string.winner)

    }

    fun swipeRight() {
        for (v in 0..15) {
            if (v != 0 && v != 4 && v != 8 && v != 12) {
                if (list[v].text == "") {
                    list[v].text = list[v - 1].text
                    list[v - 1].text = ""

                }

            }

        }
        setBackground()
        if (isEqual(getCheckList(), winList)) binding.textView.text = getString(R.string.winner)
    }

    fun swipeUp() {
        for (v in 15 downTo 0) {
            if (v != 12 && v != 13 && v != 14 && v != 15) {
                if (list[v].text == "") {
                    list[v].text = list[v + 4].text
                    list[v + 4].text = ""

                }

            }


        }
        setBackground()
        if (isEqual(getCheckList(), winList)) binding.textView.text = getString(R.string.winner)
    }

    fun swipeDown() {
        for (v in 0..15) {
            if (v != 0 && v != 1 && v != 2 && v != 3) {
                if (list[v].text == "") {
                    list[v].text = list[v - 4].text
                    list[v - 4].text = ""

                }

            }

        }
        setBackground()
        if (isEqual(getCheckList(), winList)) binding.textView.text = getString(R.string.winner)
    }


}





