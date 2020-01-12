package com.joshuapetersen.timetable.Utils

import java.io.File
import java.lang.management.ManagementFactory


class Utils
{
    companion object
    {
        val userDir = File("").canonicalPath.toString()
        val assetsDir = userDir+"\\assets"
        val DAYS: ArrayList<String> = arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        val imagesMap:HashMap<String, File> = hashMapOf(
            Pair("Ma",File("D:\\Users\\mirro\\Documents\\Projects\\Kotlin\\TimeTable\\src\\main\\resources\\assets\\images\\maths.jpg")),
            Pair("Ac",
                File("D:\\Users\\mirro\\Documents\\Projects\\Kotlin\\TimeTable\\src\\main\\resources\\assets\\images\\Assembly.gif")
            )
        )

        fun findImage(classID:String) : File?
        {
            for (entry in imagesMap)
            {
                if(classID.contains(entry.key))
                    return entry.value

            }
            return null
        }
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
