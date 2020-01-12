package com.joshuapetersen.timetable

import com.beust.klaxon.Klaxon
import com.joshuapetersen.timetable.Utils.LOG
import com.joshuapetersen.timetable.Utils.Utils
import com.joshuapetersen.timetable.data.StudentInfo
import com.joshuapetersen.timetable.ui.TimeTableVBox
import com.joshuapetersen.timetable.data.TimeTableData
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File

class Main : Application()
{
    override fun start(primaryStage: Stage?)
    {

        val timeTableData = TimeTableData
        LOG(timeTableData["Monday"].toString())

        val timeTableVBox = TimeTableVBox()
        val scene = Scene(timeTableVBox, 800.0, 600.0)
        timeTableVBox.setMinSize(scene.width, scene.height)
        timeTableVBox.isFillWidth = true
        scene.stylesheets += "file:assets/stylesheets/Styles.css"
        primaryStage?.scene = scene
        primaryStage?.title = "Time Table Application"
        primaryStage?.show()
    }

    companion object
    {
        @JvmStatic
        fun main(args: Array<String>)
        {
            LOG(Utils.assetsDir)

            launch(Main::class.java, *args)

        }
    }

}
