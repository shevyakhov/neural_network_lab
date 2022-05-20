package com.example.neural

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neural.SharedLists.sharedError
import com.example.neural.SharedLists.sharedPreciseness
import com.example.neural.databinding.ActivityDisplayBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

//вывод графика
class DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lineEntry = ArrayList<Entry>()
        val lineEntrySum = ArrayList<Entry>()

        for (i in sharedPreciseness.indices) {
            lineEntry.add(
                Entry(
                    (i).toFloat(),
                    sharedPreciseness[i]
                )
            )

        }
        for (i in sharedError.indices) {
            lineEntrySum.add(
                Entry(
                    (i).toFloat(),
                    sharedError[i]
                )
            )
        }


        val lineDataset = LineDataSet(lineEntry, "Точность")
        val lineError = LineDataSet(lineEntrySum, "Ошибка")
        lineError.color = Color.RED;
        lineError.setCircleColor(Color.RED);
        lineDataset.setCircleColor(R.color.yellow)
        lineDataset.circleRadius = 0F
        lineError.lineWidth = 2f;
        lineDataset.lineWidth = 2f;
        lineDataset.color = resources.getColor(R.color.yellow)
        val data = LineData(lineDataset, lineError)
        binding.chart.description.text = "График"
        binding.chart.setBorderColor(getColor(R.color.white))
        binding.chart.fitScreen()
        binding.chart.data = data
        binding.chart.animateXY(3000, 3000)
    }
}