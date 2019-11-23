package com.joshuapetersen.timetable

import javafx.scene.image.Image
import java.io.File
import java.io.FileInputStream

class Utils
{
    companion object
    {
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
