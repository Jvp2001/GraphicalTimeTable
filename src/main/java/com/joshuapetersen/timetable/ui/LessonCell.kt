package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.GroupInfo
import com.joshuapetersen.timetable.data.StudentLesson
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox


class LessonCell(image: Image, groupInfo: GroupInfo?) : VBox(5.0)
{
    init
    {
        style += "-fx-text-alignment: center;"

        val vBox = VBox(Label(groupInfo?.classID), Label(groupInfo?.teacherInitials), Label(groupInfo?.roomID))
        vBox.alignment = Pos.CENTER
        children.setAll(ImageView(image), vBox)
    }
}
