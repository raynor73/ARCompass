package ru.ilapin.common.math

class Vector4f(x: Float, y: Float, z: Float, w: Float = 1.0f) : Point4f(x, y, z, w) {

    constructor() : this(0.0f, 0.0f, 0.0f)

    fun length() = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
}