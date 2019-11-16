package com.joshuapetersen.timetable.data

import com.poiji.annotation.ExcelCell
import com.poiji.annotation.ExcelCellName
import com.poiji.annotation.ExcelSheet
import java.awt.SystemColor.info
@ExcelSheet(value = "")

class Lesson
{
    @ExcelCell(0)
    var year: Int? = null
    @ExcelCell(1)
    var className: String? = null
    @ExcelCell(2)
    var pupilName: String? = null
    @ExcelCell(3)
    var day: String? = null
    @ExcelCellName("Period Name")
    var periodName: String? = null
    @ExcelCell(5)
    var week: Int? = null
    @ExcelCell(6)
    var groupInfo: String? = null

    val UNKOWN: String = "Unknown"
    private val subData: List<String>?
        get() = this.groupInfo?.split(":")

    val classID: String
        get() = subData?.get(0) ?: UNKOWN
    val teachersInitials: String
        get() = subData?.get(1) ?: UNKOWN

    val roomID: String
        get() = (if (subData?.get(2).toString() == "-") UNKOWN else subData?.get(2).toString())




    override fun toString(): String
    {
        return "Lesson(year=$year, className=$className, pupilName=$pupilName, day=$day, periodName=$periodName, week=$week, groupInfo=$groupInfo, subData=$subData, classID='$classID', teachersInitials='$teachersInitials', roomID='$roomID')"
    }


}