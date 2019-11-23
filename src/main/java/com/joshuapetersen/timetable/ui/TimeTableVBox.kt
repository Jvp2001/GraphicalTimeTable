package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.TimeTableData
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment

class TimeTableVBox : VBox(7.0)
{
    init
    {
        val timeTablePane = TimeTablePane()

        setVgrow(timeTablePane,Priority.ALWAYS)
        timeTablePane.setMinSize(this.minWidth,this.minHeight)
        val titleLabel = Label(TimeTableData.studentLessons!![0].pupilName)
        titleLabel.styleClass += "Title"
        titleLabel.styleClass += "Bold"
        titleLabel.maxWidth = Double.MAX_VALUE
        titleLabel.alignment = Pos.CENTER
        children.setAll(titleLabel,timeTablePane)
    }
}