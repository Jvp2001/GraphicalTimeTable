package com.joshuapetersen.timetable.ui

import com.joshuapetersen.timetable.data.Lesson
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox


class LessonCell(image: Image,lesson: Lesson) : VBox(5.0)
{
    init
    {
        style += "-fx-text-alignment: center;"

        val vBox = VBox(Label(lesson.classID), Label(lesson.teachersInitials), Label(lesson.roomID))
        vBox.alignment = Pos.CENTER
        children.setAll(ImageView(image), vBox)
    }
}