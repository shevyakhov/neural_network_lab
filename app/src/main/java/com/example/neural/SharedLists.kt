package com.example.neural

import kotlin.random.Random

object SharedLists {
    var sharedWeight1 = Array(3) { DoubleArray(3) { Random.nextDouble(0.0, 1.0) } }
    var sharedOffSet1 = DoubleArray(3) { Random.nextDouble(0.0, 1.0) } // смещение
    var sharedWeight2 = Array(3) { DoubleArray(3) { Random.nextDouble(0.0, 1.0) } }
    var sharedOffSet2 = DoubleArray(3) { Random.nextDouble(0.0, 1.0) }  // смещение
    var sharedError =ArrayList<Float>()
    var sharedPreciseness =ArrayList<Float>()
}