package com.joshuapetersen.timetable.data

import com.joshuapetersen.timetable.Utils
import com.poiji.bind.Poiji
import com.poiji.option.PoijiOptions
import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class TimeTableData()
{
    private val daysMap: HashMap<String, MutableList<Lesson>> = HashMap()
    private val studentName:String
    companion object
    {
        private val options = PoijiOptions.PoijiOptionsBuilder.settings().limit(49).build()
        val studentLessons: MutableList<Lesson>? =
            parseStudents()

        private fun parseStudents(): MutableList<Lesson>?
        {
            return Poiji.fromExcel(
                File(
                    """D:\Users\mirro\Documents\Projects\Kotlin\TimeTable\src\main\resources\data\pupil-lesson-list HE.xlsx"""
                ),
                Lesson::class.java,
                options
            )
        }
        fun findLessonForDay(day:String) : ArrayList<Lesson>
        {
            val lessons: ArrayList<Lesson> = arrayListOf()
            for (lesson in studentLessons!!)
            {
                if(lesson.day.equals(day,ignoreCase = true))
                {
                    lessons.add(lesson)
                }
            }
//            lessons.sort()
            return lessons
        }
    }

    init
    {
        studentName = studentLessons?.get(0)?.pupilName!!
        for (day in Utils.DAYS)
        {
            addItem(day, studentLessons!!)
        }

    }

    operator fun get(key: String): MutableList<Lesson>? = daysMap[key]

    private fun addItem(keyName: String, lessons: MutableList<Lesson>)
    {
        val specificLessons = ArrayList<Lesson>(10)
        for (lesson in lessons)
        {
            if (keyName == lesson.day)
            {
                specificLessons.add(lesson)
            }
        }
        daysMap.put(keyName, specificLessons)
    }


}

