package com.ssy

/**
 * Custom annotation indicates that the file/property/class is from Official doc
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class Official()
