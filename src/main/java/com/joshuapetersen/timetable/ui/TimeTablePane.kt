package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.Lesson
import com.joshuapetersen.timetable.data.TimeTableData
import com.joshuapetersen.timetable.data.GlobalData.Companion.Images.Companion.subjectImagesMap
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment


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
        headers.withIndex().forEach { (index, header) ->
            addColumn(index + 1, header)
        }
    }

    private fun createDayLabel(day: String): Label
    {
        val label = Label(day)

        label.textAlignment = TextAlignment.CENTER
        return label
    }

    fun createWeekColumn()
    {
        for ((index, i) in arrayListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday").withIndex())
        {
            add(createDayLabel(i), 0, index + 1)
        }
    }

    private fun populateGrid()
    {
        addColumn(0, Label("Weekday"))
        createHeaderRow()
        createWeekColumn()
        var row = 1
        while (row <= 5)
        {
            (1..11).forEach { i ->
                    val lesson = TimeTableData.student?.lessons?.get(i)
                    add(
                        LessonCell(
                            Image(
                                TimeTableData.findSubjectImagePath(lesson!!.groupInfo.classID.split( " ")[1]) ,
                                imageSize.width,
                                imageSize.height,
                                true,
                                true
                            ), lesson!!.groupInfo
                        ),
                        i, row
                    )
                }
            row++
        }
    }

    fun createImage(subjectName: String): Image =
        Image(subjectImagesMap[subjectName], imageSize.width, imageSize.height, true, true)

}
