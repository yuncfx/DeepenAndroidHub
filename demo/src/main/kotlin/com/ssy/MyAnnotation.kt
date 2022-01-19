package com.ssy

/**
 * Custom annotation indicates that the file/property/class is from Official doc.
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class Official()

/**
 * Custom annotation indicates that the file/class is from WeChat public account articles.
 */
@Target(AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class PublicAccount(val depict: String)
