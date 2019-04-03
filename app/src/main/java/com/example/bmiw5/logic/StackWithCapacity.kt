package com.example.bmi.logic

class StackWithCapacity<T>(val capacity: Int) {

    private val ar: Array<T> = arrayOfNulls<Any>(capacity) as Array<T>
    private var top: Int = 0
    private var noOfElementsStored = 0

    fun push(newObj: T) {
        ar[top++] = newObj
        top = top % ar.size
        if (noOfElementsStored < capacity) {
            noOfElementsStored += 1
        }
    }

    override fun toString(): String {
        var strBuf: StringBuffer = StringBuffer()

        for (i in 0 until noOfElementsStored) {
            val indexToGetAt = (top - 1 - i + capacity) % capacity
            strBuf.append(ar[indexToGetAt])
        }

        return strBuf.toString()
    }

    fun toArrayList(): ArrayList<T> {
        var arl: ArrayList<T> = ArrayList<T>()

        for (i in 0 until noOfElementsStored) {
            val indexToGetAt = (top - 1 - i + capacity) % capacity
            arl.add(ar[indexToGetAt])
        }
        return arl
    }

}