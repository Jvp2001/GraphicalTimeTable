package com.joshuapetersen.timetable.data.parser

import java.io.File
import kotlin.reflect.KClass

interface FileParser
{
    fun <T:Any> parseFile(file:String,clazz: KClass<T>)
    fun <T:Any> parseFile(file: File,clazz: KClass<T>)
}