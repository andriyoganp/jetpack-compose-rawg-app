package com.rawg.core.utils

import com.google.gson.Gson

fun <T : Any> T.convertToJson(): String = Gson().toJson(this)

inline fun <reified T : Any> String?.fromJson(): T = Gson().fromJson(this, T::class.java)