package examples

// with class
class Counter(private var currentNumber: Int) {
    init {
        currentNumber -= 2
    }
    fun countByTwo(): Int {
        currentNumber += 2
        return currentNumber
    }
}

// with function
fun getCountByTwo(startNumber: Int): ()-> Int {
    var currentNumber = startNumber - 2
    return {
        currentNumber += 2
        currentNumber
    }
}


fun main(args: Array<String>){
    // with function
    val getCountByTwo = getCountByTwo(0)
    println(getCountByTwo()) // 2
    println(getCountByTwo()) // 4
    println(getCountByTwo()) // 6
    println(getCountByTwo()) // 8


    // with class
    val myClassCounter = Counter(0)
    println(myClassCounter.countByTwo()) // 2
    println(myClassCounter.countByTwo()) // 4
    println(myClassCounter.countByTwo()) // 6
    println(myClassCounter.countByTwo()) // 8
}
