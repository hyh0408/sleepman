package com.sleepman.kotlin

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val persons = listOf(Person("영희"), Person("철수", 20))
    val oldest = persons.maxBy { it.age ?: 0 }
    println("나이가 가장 많은 사람은 : $oldest")
}

//open class PropertyChangeAware {
//    protected val changeSupport = PropertyChangeSupport(this)
//    fun addPropertyChangeListener(listener: PropertyChangeListener) {
//        changeSupport.addPropertyChangeListener(listener)
//    }
//
//    fun removePropertyChangeListener(listener: PropertyChangeListener) {
//        changeSupport.removePropertyChangeListener(listener)
//    }
//}
//
//class Person {
//    private val _attributes = hashMapOf<String, String>()
//    fun setAttribute(attrName: String, value: String) {
//        _attributes[attrName] = value
//    }
//
//    val name: String by _attributes
//}

//object Users : IdTable()
//
data class Person(
    val name: String,
    val age: Int? = null
)
