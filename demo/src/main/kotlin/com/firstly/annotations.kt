package com.firstly

import kotlin.annotation.AnnotationRetention.RUNTIME

@Target(AnnotationTarget.FUNCTION)
@Retention(RUNTIME)
annotation class GET(val value: String)

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(RUNTIME)
annotation class Field(val value: String)