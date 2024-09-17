package com.example.project1.clases

//class Variables {
fun main() {
    //Variables numericas
    val age:Int = 21
    val long_number:Long = 789324789347
    val temperature:Float = 27.123f
    val weight:Double = 60.4

    //Variables string
    val gender:Char = 'M'
    val name:String = "Jesus Uriel Flores Zavala"

    //Boleanos
    //val isGreater:Boolean false

    //array
    val names = arrayOf("Erik", "Silvia", "Hector", "Gabriela")

    println(age)
    println("Welcome $name, to your first kotlin project")
    println(add())
    println(product(10,92))
    printArray(names)

    println(names.joinToString())

    println(" ")
    val numbers = arrayOf(1,2,3,4,5,6,7,8,9)
    isEven(numbers)
    println(getDay(4))

    val person = Person("Andres",22)

    println(" ")
    println(person.name)
    println(person.age)

    person.displayInformation()
}

fun add():Int{
    val x = 5
    val y = 10

    return x + y
}

fun product(x:Int, y:Int):Int{
    return x + y
}

//(nombreDato:Array<Tipo de array>)
fun printArray(names:Array<String>){
    for(name in names){
        println("Hello $name")
    }
}

fun isEven(numbers:Array<Int>){
    for (number in numbers){
        if (number % 2 == 0){
            println("The number $number is Even")
        } else {
            println("The number $number is Odd")
        }
    }
}

fun getDay(day:Int): String {
    var name = ""

    when(day){
        //Cuando dia = 1
        1 -> name = "Monday"
        2 -> name = "Thusday"
        3 -> name = "Wednesday"
        4 -> name = "Thursday"
        5 -> name = "Friday"
        6 -> name = "Saturday"
        7 -> name = "Sunday"
        else -> name = "Incorrect value"
    }

    return name
}

class Person (val name: String, val age: Int){
    fun displayInformation(){
        println("Name: $name, Age: $age")
    }
}
//}