package com.example.mockprogram1

fun findSecondLargestNumber(arr:IntArray){

    val length = arr.size
    var secondLargest:Int
    var i = 0

    if(length<2){
        print("Invalid Input")
        return
    }

    secondLargest = Int.MIN_VALUE
    var largest = secondLargest

    for(i in 0 until length){
        largest = Math.max(largest, arr[i])
    }

    while(i in 0 until length){
        if(arr[i] != largest){
            secondLargest = Math.max(secondLargest,arr[i])
        }
        i++
    }
    print("The second largest number is $secondLargest")
}

fun main(){
    val arr = intArrayOf(11,33,22,44,66,55)
    findSecondLargestNumber(arr)
}