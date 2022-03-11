package com.ssy

/**
 * The Custom annotation indicates that file/class is from official documentation
 */
@Target(AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class Official(val url: String = "")

@Target(AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class OfficialRelative(val url: String = "")

/**
 * The Custom annotation indicates that file/class is from WeChat public account articles.
 */
@Target(AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class PublicAccount(val depict: String = "")

/**
 * The Custom annotation indicates that file/class is supplemented by official documentation
 */
@Target(AnnotationTarget.FILE, AnnotationTarget.CLASS)
annotation class OfficialSupplement(val depict: String = "")
