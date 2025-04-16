package com.wilfredo.poke.module.utils

internal fun String.toLowercase(): String {
    if (this.isEmpty()) return this
    return this.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}