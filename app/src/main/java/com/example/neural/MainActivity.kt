package com.example.neural

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.neural.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var epochNum = 1
    private var learningRate = 0.25
    private var firstImg = R.drawable.zero
    private var secondImg = R.drawable.zero
    private var thirdImg = R.drawable.zero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()


    }

    private fun setUI() {
        binding.graph.setOnClickListener {
            intent = Intent(this, DisplayActivity::class.java)
            startActivity(intent)
        }
        binding.checkBtn.setOnClickListener {
            val one = oneOrZero(1)
            val two = oneOrZero(2)
            val three = oneOrZero(3)
            val list = arrayOf(one, two, three)
            Toast.makeText(this, testInput(list), Toast.LENGTH_LONG).show()
        }
        binding.learnBtn.setOnClickListener {
            learning(epochNum, learningRate)
        }
        binding.tileOne.setOnClickListener {
            if (firstImg == R.drawable.zero) {
                firstImg = R.drawable.one
                binding.tileOne.setImageResource(firstImg)
            } else {
                firstImg = R.drawable.zero
                binding.tileOne.setImageResource(firstImg)
            }
        }

        binding.tileTwo.setOnClickListener {
            if (secondImg == R.drawable.zero) {
                secondImg = R.drawable.one
                binding.tileTwo.setImageResource(secondImg)
            } else {
                secondImg = R.drawable.zero
                binding.tileTwo.setImageResource(secondImg)
            }
        }
        binding.tileThree.setOnClickListener {
            if (thirdImg == R.drawable.zero) {
                thirdImg = R.drawable.one
                binding.tileThree.setImageResource(thirdImg)
            } else {
                thirdImg = R.drawable.zero
                binding.tileThree.setImageResource(thirdImg)
            }
        }


        binding.epochSeekbar.max = 1000
        binding.learningRateSeekBar.max = 100

        binding.epochSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.epochText.text = binding.epochSeekbar.progress.toString()
                epochNum = binding.epochSeekbar.progress
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.e("touch", "click")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.learningRateSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                binding.learnRateText.text =
                    (binding.learningRateSeekBar.progress.toDouble() / 100).toString()
                learningRate = binding.learningRateSeekBar.progress.toDouble() / 100
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.e("touch", "click")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

    private fun oneOrZero(index: Int): Int {
        return when (index) {
            1 -> if (firstImg == R.drawable.zero) 0 else 1
            2 -> if (secondImg == R.drawable.zero) 0 else 1
            3 -> if (thirdImg == R.drawable.zero) 0 else 1
            else -> 0
        }
    }
}

