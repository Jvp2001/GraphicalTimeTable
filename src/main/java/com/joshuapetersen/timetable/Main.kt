package com.joshuapetersen.timetable

import com.joshuapetersen.timetable.data.Lesson
import com.poiji.bind.Poiji
import com.poiji.option.PoijiOptions.PoijiOptionsBuilder
import javafx.application.Application
import javafx.stage.Stage
import java.io.File

class Main : Application()
{
    override fun start(primaryStage: Stage?)
    {
        val options = PoijiOptionsBuilder.settings()
            .preferNullOverDefault(false)
            .ignoreHiddenSheets(false)
            .limit(49)
            .build()
        val students: MutableList<Lesson> = Poiji.fromExcel(
            File(
                """D:\Users\mirro\Documents\Projects\Kotlin\TimeTable\src\main\resources\data\pupil-lesson-list HE.xlsx"""
            ),
            Lesson::class.java,
            options
        )
        for (student in students)
        {
            println(student)
        }
    }

    companion object
    {
        @JvmStatic
        fun main(args: Array<String>)
        {
            launch(Main::class.java, *args)
        }
    }

}
