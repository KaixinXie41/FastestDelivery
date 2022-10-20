package com.example.mockprogram1

class SecondConstructor(var stuName:String, var stuId:Int, var stuAge:Int){

    init {
        stuName =""
        stuId = 0
    }

    constructor(full_name:String, id:Int) : this(full_name, id, 15) {
         this.stuName = full_name
         this.stuId = id
        println("Name = $stuName")
        println("Id = $stuId")
        println("age = $stuAge")
    }
}

fun main(){
    SecondConstructor("Kaixin", 41)
}

