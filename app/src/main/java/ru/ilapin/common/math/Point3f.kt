package ru.ilapin.common.math

open class Point3f(x: Float, y: Float, z: Float) {

    constructor() : this(0.0f, 0.0f, 0.0f)

    val values = FloatArray(3)

    var x: Float
        get() = values[0]
        set(value) { values[0] = value }

    var y: Float
        get() = values[1]
        set(value) { values[1] = value }

    var z: Float
        get() = values[2]
        set(value) { values[2] = value }

    init {
        this.x = x
        this.y = y
        this.z = z
    }
}