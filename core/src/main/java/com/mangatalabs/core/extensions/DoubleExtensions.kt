package com.mangatalabs.core.extensions

fun Double.round(decimals: Int = 2): String = "%.${decimals}f".format(this)