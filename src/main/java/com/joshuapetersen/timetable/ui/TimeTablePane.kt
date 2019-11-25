package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.Lesson
import com.joshuapetersen.timetable.data.TimeTableData
import com.joshuapetersen.timetable.data.imageRefMap
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import java.time.DayOfWeek


class TimeTablePane : GridPane()
{

    private val imageSize = object
    {
        val width = 75.0
        val height = 75.0
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

        populateGrid()

    }

    private fun createHeaderRow()
    {
        for ((index, header) in headers.withIndex())
        {
            addColumn(index+1, header)
        }
    }

    private fun createDayLabel(day: String): Label
    {
        val label = Label(day)

        label.textAlignment = TextAlignment.CENTER
        return label
    }

    fun addSubject(rowIndex: Int, columnIndex: Int)
    {
        add(createLessonCell(), columnIndex, rowIndex)
    }

    fun createLessonCell(): LessonCell =
        LessonCell(
            Image(imageRefMap["Maths"], imageSize.width, imageSize.height, true, true),
            TimeTableData.findLessonForDay("Monday")[1]
        )

    fun createWeekColumn()
    {
        for ((index, i) in arrayListOf("Monday","Tuesday","Wednesday","Thursday","Friday").withIndex())
        {
            add(createDayLabel(i), 0,index+1)
        }
    }

    fun populateGrid()
    {
        addColumn(0,Label("Weekday"))
        createHeaderRow()
        createWeekColumn()
        var row = 1
        while(row <= 5)
        {
            for (i in 1..11)
            {
                addSubject(row, i);
            }
            row++
        }
    }

    fun createImage(subjectName: String): Image =
        Image(imageRefMap[subjectName], imageSize.width, imageSize.height, true, true)

}