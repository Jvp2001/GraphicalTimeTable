package com.joshuapetersen.timetable.Utils

import java.io.File
import java.lang.management.ManagementFactory
import java.nio.file.Paths


class Utils
{
    companion object
    {
        val userDir = File("").canonicalPath.toString()
        val assetsDir = Paths.get(userDir,"assets").toAbsolutePath().toString()
        val imagesDir = Paths.get(assetsDir, "images").toAbsolutePath().toString()

        val DAYS: ArrayList<String> = arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

    }
}

var isDebug =
    ManagementFactory.getRuntimeMXBean().inputArguments.toString().indexOf("-agentlib:jdwp") > 0
fun LOG(msg:Any)
{
   if(isDebug) println(msg)
}
fun LOG(msg:List<Any>)
{
    if(isDebug) println(msg)
}
