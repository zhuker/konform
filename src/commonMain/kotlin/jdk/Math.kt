package jdk

import kotlin.math.pow

object Math {
    const val PI: Double = kotlin.math.PI

    inline fun min(a: Int, b: Int): Int = kotlin.math.min(a, b)
    inline fun min(a: Float, b: Float): Float = kotlin.math.min(a, b)
    inline fun min(a: Double, b: Double): Double = kotlin.math.min(a, b)
    inline fun max(a: Int, b: Int): Int = kotlin.math.max(a, b)
    inline fun max(a: Float, b: Float): Float = kotlin.math.max(a, b)
    inline fun max(a: Double, b: Double): Double = kotlin.math.max(a, b)
    inline fun log10(x: Double): Double = kotlin.math.log10(x)
    inline fun pow(x: Double, pow: Double): Double = x.pow(pow)
    inline fun abs(x: Int): Int = kotlin.math.abs(x)
    inline fun abs(x: Float): Float = kotlin.math.abs(x)
    inline fun ceil(x: Double): Double = kotlin.math.ceil(x)
    inline fun sqrt(x: Double): Double = kotlin.math.sqrt(x)
    inline fun sqrt(x: Float): Float = kotlin.math.sqrt(x)
    inline fun cos(x: Double): Double = kotlin.math.cos(x)
    inline fun cos(x: Float): Float = kotlin.math.cos(x)
    inline fun sin(x: Double): Double = kotlin.math.sin(x)
    inline fun sin(x: Float): Float = kotlin.math.sin(x)
    inline fun exp(x: Double): Double = kotlin.math.exp(x)
    inline fun abs(x: Double): Double = kotlin.math.abs(x)
    inline fun floor(x: Double): Double = kotlin.math.floor(x)
    inline fun tan(x: Double): Double = kotlin.math.tan(x)
    inline fun atan(x: Double): Double = kotlin.math.atan(x)
    inline fun log(d: Double): Double = kotlin.math.ln(d)
    inline fun round(d: Double): Long = kotlin.math.round(d).toLong()
}