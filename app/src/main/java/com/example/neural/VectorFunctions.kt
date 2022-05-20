package com.example.neural

import com.example.neural.SharedLists.sharedError
import com.example.neural.SharedLists.sharedOffSet1
import com.example.neural.SharedLists.sharedOffSet2
import com.example.neural.SharedLists.sharedPreciseness
import com.example.neural.SharedLists.sharedWeight1
import com.example.neural.SharedLists.sharedWeight2
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.exp
import kotlin.random.Random

//превращение матрицы ответов в вектор
fun vectorised(answersOutput: Array<Double>): Array<DoubleArray> {
    val arr = Array(answersOutput.size) { DoubleArray(3) { 0.0 } }
    for (i in answersOutput.indices) {
        arr[i][(answersOutput[i] - 1).toInt()] = 1.0
    }
    return arr
}

//функция активации
fun sigma(vector: Array<DoubleArray>): Array<DoubleArray> {
    for (i in vector.indices) {
        for (j in vector[i].indices) {
            vector[i][j] = 1 / (1 + exp(-1 * vector[i][j]))
        }
    }
    return vector
}

//функция обратная функции активации
fun sigmaDerivative(vector: Array<DoubleArray>): Array<DoubleArray> {
    val temp = clone(vector)
    val temp1 = clone(vector)
    return elementMultiply(sigma(temp1), minus(1, sigma(temp)))
}

//векторное умножение матриц
fun multiply(first: Array<DoubleArray>, second: Array<DoubleArray>): Array<DoubleArray> {

    val product = Array(first.size) { DoubleArray(second[0].size) }
    for (i in first.indices) {
        for (j in second[0].indices) {
            for (k in 0 until first[0].size) {
                product[i][j] += first[i][k] * second[k][j]
            }
        }
    }

    return product
}

fun multiply(
    first: Array<Array<Int>>,
    second: Array<DoubleArray>
): Array<DoubleArray> {
    val product = Array(first.size) { DoubleArray(second[0].size) }
    for (i in first.indices) {
        for (j in second[0].indices) {
            for (k in 0 until first[0].size) {
                product[i][j] += first[i][k] * second[k][j]
            }
        }
    }

    return product
}

fun multiply(
    first: Array<Int>,
    second: Array<DoubleArray>
): DoubleArray {
    val product = DoubleArray(second[0].size) { 0.0 }
    for (i in first.indices) {
        var sum = 0.0
        for (j in second[0].indices) {
            sum += first[j] * second[j][i]
        }
        product[i] = sum
    }

    return product
}

fun multiply(num: Double, arr: Array<DoubleArray>): Array<DoubleArray> {
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            arr[i][j] *= num
        }
    }
    return arr
}

fun multiply(num: Double, arr: DoubleArray): DoubleArray {
    for (i in arr.indices) {
        arr[i] *= num
    }
    return arr
}

//поэлементное умножение матриц
fun elementMultiply(
    first: Array<DoubleArray>,
    second: Array<DoubleArray>
): Array<DoubleArray> {
    val product = Array(first.size) { DoubleArray(second[0].size) }
    for (i in first.indices) {
        for (j in first[0].indices) {
            product[i][j] = first[i][j] * second[i][j]
        }
    }

    return product
}

//сумма двух массивов
fun sum(
    firstMatrix: Array<DoubleArray>,
    secondMatrix: DoubleArray
): Array<DoubleArray> {
    for (i in firstMatrix.indices) {
        for (j in firstMatrix[0].indices) {
            firstMatrix[i][j] += secondMatrix[j]
        }

    }

    return firstMatrix
}

//сумма значений по оси x
fun collapseSum(arr: Array<DoubleArray>): DoubleArray {
    val summed = DoubleArray(arr[0].size) { 0.0 }

    for (row in arr[0].indices) {
        var sum = 0.0
        for (col in arr.indices) {
            sum += arr[col][row]
        }
        summed[row] = sum
    }
    return summed
}

//функции транспонирование матрицы
fun transpose(arr: Array<DoubleArray>): Array<DoubleArray> {
    val row = arr[0].size
    val column = arr.size
    val transpose = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            transpose[i][j] = arr[j][i]
        }
    }
    return transpose
}

fun transpose(arr: Array<Array<Int>>): Array<DoubleArray> {
    val row = arr[0].size
    val column = arr.size
    val transpose = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            transpose[i][j] = arr[j][i].toDouble()
        }
    }
    return transpose
}

//арифметические функции вычитания
fun minus(first: DoubleArray, second: DoubleArray): DoubleArray {
    for (i in first.indices) {
        first[i] -= second[i]
    }
    return first
}

fun minus(num: Int, vector: Array<DoubleArray>): Array<DoubleArray> {
    for (i in vector.indices) {
        for (j in vector[i].indices) {
            vector[i][j] = num - vector[i][j]
        }
    }
    return vector
}

fun minus(
    firstMatrix: Array<DoubleArray>,
    secondMatrix: DoubleArray
): Array<DoubleArray> {
    for (i in firstMatrix.indices) {
        for (j in firstMatrix[0].indices) {
            firstMatrix[i][j] -= secondMatrix[j]
        }

    }
    return firstMatrix
}

fun minus(
    firstMatrix: Array<DoubleArray>,
    secondMatrix: Array<DoubleArray>
): Array<DoubleArray> {
    for (i in firstMatrix.indices) {
        for (j in firstMatrix[0].indices) {
            firstMatrix[i][j] = firstMatrix[i][j] - secondMatrix[i][j]
        }
    }
    return firstMatrix
}

//функции вывода на экран
fun print(m: Array<DoubleArray>) {
    for (i in m) {
        for (j in i) {
            print(j)
            print(" ")
        }
        println()
    }
}

fun print(m: Array<Int>) {
    for (i in m) {
        print(i)
        print(" ")
    }
    println()
}

fun print(m: DoubleArray) {
    for (i in m) {
        print(i)
        print(" ")
    }
    println()
}

//нахождение максимума в массиве
fun findMax(arr: Array<DoubleArray>): Int {
    var maxInd = 0
    for (i in arr[0].indices) {
        if (arr[0][maxInd] < arr[0][i])
            maxInd = i
    }
    return maxInd
}

// функция копирования массива
fun clone(arr: Array<DoubleArray>): Array<DoubleArray> {
    val temp = Array(arr.size) { DoubleArray(arr[0].size) { 0.0 } }
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            temp[i][j] = arr[i][j]
        }
    }
    return temp
}

//функция тестирования входного значения на обученной сети
fun testInput(input: Array<Int>): String {
    val outputHiddenLayer = sum(arrayOf(multiply(input, sharedWeight1)), sharedOffSet1)
    val sigma = sigma(outputHiddenLayer)
    val outputLayer = sum(multiply(sigma, sharedWeight2), sharedOffSet2)
    val sigmaOutputLayer = sigma(outputLayer)
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.DOWN
    var result = "["
    for (i in sigmaOutputLayer[0].indices) {
        result += df.format(sigmaOutputLayer[0][i]) + ", "
    }
    result += "]"
    return result
}

//Обучение нейронной сети
fun learning(epochNum: Int, learningRate: Double) {
    val answersInput = arrayOf(
        arrayOf(1, 1, 0),
        arrayOf(1, 0, 1),
        arrayOf(0, 1, 1),
        arrayOf(0, 1, 0),
        arrayOf(0, 1, 1),
        arrayOf(0, 0, 1),
        arrayOf(0, 1, 0),
        arrayOf(1, 1, 1),
        arrayOf(0, 0, 0)
    )
    val answersOutput = arrayOf(
        1.0, 1.0, 2.0, 3.0, 2.0, 3.0, 3.0, 1.0, 2.0
    )
    val preciseness = ArrayList<Float>()
    val error = ArrayList<Float>()

    var weight = Array(3) { DoubleArray(3) { Random.nextDouble(0.0, 1.0) } }
    var offSet = DoubleArray(3) { Random.nextDouble(0.0, 1.0) } // смещение
    var weight2 = Array(3) { DoubleArray(3) { Random.nextDouble(0.0, 1.0) } }
    var offSet2 = DoubleArray(3) { Random.nextDouble(0.0, 1.0) }  // смещение

    for (epoch in 0..epochNum) {
        var outputHiddenLayer = sum(
            multiply(answersInput, weight),
            offSet
        ) // перемножаем веса на входные значения и добавляем смещение
        val temp = clone(outputHiddenLayer)
        var sigma = sigma(temp) // преобразуем значения от 0 до 1

        var outputLayer = sum(multiply(sigma, weight2), offSet2) // получаем значения выходного слоя

        var sigmaOutputLayer = sigma(outputLayer) // получаем итоговые значения в промежутке 0..1

        val rightAnswerVector = vectorised(answersOutput) // проебразуем правильные ответы в вектор

        //идем назад по всей сети
        val backFromSigmaLayer = minus(
            sigmaOutputLayer,
            rightAnswerVector
        ) //вычисляем отдаленность от правильного ответа

        val backDerivativeWeight = multiply(
            transpose(sigma),
            backFromSigmaLayer
        ) // определяем значение производной в точках весов
        val neuronOffset = collapseSum(backFromSigmaLayer)//определяем смещение для нейрона

        val newSigmaHiddenLayer = multiply(backFromSigmaLayer, (transpose(weight2)))
        val newHiddenLayer =
            elementMultiply(newSigmaHiddenLayer, sigmaDerivative(outputHiddenLayer))
        val newWeights = multiply(transpose(answersInput), newHiddenLayer)
        val newOffset = collapseSum(newHiddenLayer)


        weight = minus(weight, multiply(learningRate, newWeights))

        offSet = minus(offSet, multiply(learningRate, newOffset))

        weight2 = minus(weight2, multiply(learningRate, backDerivativeWeight))

        offSet2 = minus(offSet2, multiply(learningRate, neuronOffset))

        var counter = 0
        for (i in answersInput.indices) {

            outputHiddenLayer = sum(arrayOf(multiply(answersInput[i], weight)), offSet)
            sigma = sigma(outputHiddenLayer)
            outputLayer = sum(multiply(sigma, weight2), offSet2)
            sigmaOutputLayer = sigma(outputLayer)
            if ((findMax(sigmaOutputLayer) + 1).toDouble() == answersOutput[i])
                counter++

        }
        if ((epoch + 1) % 10 == 0) {
            println("Точность на эпохе ${epoch + 1} =  ${counter.toFloat() / 9}")
            preciseness.add(counter.toFloat() / 9)
            error.add(1 - counter.toFloat() / 9)
        }
        if (counter.toDouble() / 9 == 1.0) {
            println("Точность на эпохе ${epoch + 1} =  ${counter.toFloat() / 9}")
            preciseness.add(counter.toFloat() / 9)
            error.add(1 - counter.toFloat() / 9)
            break
        }

    }
    sharedWeight1 = weight
    sharedOffSet1 = offSet
    sharedWeight2 = weight2
    sharedOffSet2 = offSet2
    sharedPreciseness = preciseness
    sharedError = error
}

