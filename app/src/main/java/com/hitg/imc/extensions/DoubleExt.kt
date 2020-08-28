package com.hitg.imc.extensions

fun Double.format(digits: Int) = String.format("%.${digits}f", this)