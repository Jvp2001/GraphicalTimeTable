package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.Lesson
import com.joshuapetersen.timetable.data.TimeTableData
import com.joshuapetersen.timetable.data.imageRefMap
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*


class TimeTablePane : GridPane()
{

    private val imageSize = object
    {
        val width = 125.0
        val height = 125.0
    }
    private val headers: ArrayList<PeriodInfoCell> = arrayListOf(
        PeriodInfoCell(
            "Reg", Lesson.findTimeRange("AMReg")
        ),
        PeriodInfoCell("Tut", Lesson.findTimeRange("Tut")), PeriodInfoCell("2", Lesson.findTimeRange("2")),
        PeriodInfoCell("Break", "10:40-11:00"),
        PeriodInfoCell("3", Lesson.findTimeRange("3")), PeriodInfoCell("4", Lesson.findTimeRange("4")),
        PeriodInfoCell("Lunch", "12:50-13:40"),
        PeriodInfoCell("Reg", Lesson.findTimeRange("PMReg")),
        PeriodInfoCell("5", Lesson.findTimeRange("5")), PeriodInfoCell("6", Lesson.findTimeRange("6")),
        PeriodInfoCell("7", Lesson.findTimeRange("7"))
    )

    init
    {
        style += "-fx-grid-lines-visible: true;"
//        val columnConstraints = ColumnConstraints()
//        columnConstraints.hgrow = Priority.ALWAYS
//        val rowConstraints = RowConstraints()
//        rowConstraints.vgrow = Priority.ALWAYS
//
//        this.columnConstraints += columnConstraints
//        this.rowConstraints += rowConstraints

        padding = Insets(10.0, 10.0, 10.0, 10.0)

        for ((index, header) in headers.withIndex())
        {
            addColumn(index, header)
        }
        add(createDayLabel(), 0, 1)
        for(i in 1..10)
        {
            addSubject(i)
        }
    }

    private fun createDayLabel(): Label
    {
        val label = Label("Monday")
        label.alignment = Pos.CENTER
        return label
    }

    fun addSubject(columnIndex: Int)
    {
        add(createLessonCell(), columnIndex, 1)
    }

    fun createLessonCell(): LessonCell =
        LessonCell(
            Image(imageRefMap["Maths"], 150.0, 150.0, true, true),
            TimeTableData.findLessonForDay("Monday")[1]
        )

    fun createImage(subjectName: String): Image =
        Image(imageRefMap[subjectName], imageSize.width, imageSize.height, true, true)

}