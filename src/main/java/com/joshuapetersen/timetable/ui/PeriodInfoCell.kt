package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.Lesson
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.BorderStroke
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.VBox
import java.awt.Paint

class PeriodInfoCell(periodName:String,lessonTime:String) : VBox(5.0)
{
    init
    {
        val data = arrayListOf(periodName,lessonTime.split("-")[0],lessonTime.split("-")[1])
        this.alignment = Pos.CENTER
        for (datum in data)
        {
            val label = Label(datum)
            children += label
        }
        style += "-fx-text-alignment: center;"


    }
}