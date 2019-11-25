package com.joshuapetersen.timetable

import com.joshuapetersen.timetable.ui.TimeTableVBox
import com.joshuapetersen.timetable.data.TimeTableData
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application()
{
    override fun start(primaryStage: Stage?)
    {

        val timeTableData = TimeTableData()
        println(timeTableData["Monday"])

        val timeTableVBox = TimeTableVBox()
        val scene = Scene(timeTableVBox, 800.0, 600.0)
        timeTableVBox.setMinSize(scene.width,scene.height)
        timeTableVBox.isFillWidth = true
        scene.stylesheets += "assets/stylesheets/Styles.css"
        primaryStage?.scene = scene
        primaryStage?.title = "Time Table Application"
        primaryStage?.show()
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
