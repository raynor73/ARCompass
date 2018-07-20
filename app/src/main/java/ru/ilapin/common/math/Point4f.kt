package ru.ilapin.common.math

open class Point4f(x: Float, y: Float, z: Float, w: Float) {

    val values = FloatArray(4)

    var x: Float
        get() = values[0]
        set(value) { values[0] = value }

    var y: Float
        get() = values[1]
        set(value) { values[1] = value }

    var z: Float
        get() = values[2]
        set(value) { values[2] = value }

    var w: Float
        get() = values[3]
        set(value) { values[3] = value }

    init {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }
}