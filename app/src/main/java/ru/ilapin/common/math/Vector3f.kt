package ru.ilapin.common.math

class Vector3f(x: Float, y: Float, z: Float) : Point3f(x, y, z) {

    constructor() : this(0.0f, 0.0f, 0.0f)

    fun length() = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
}