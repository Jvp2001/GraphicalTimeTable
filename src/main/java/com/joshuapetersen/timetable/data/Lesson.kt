package com.joshuapetersen.timetable.data

import com.poiji.annotation.ExcelCell
import com.poiji.annotation.ExcelCellName
import com.poiji.annotation.ExcelSheet
import java.awt.SystemColor.info
class Lesson : Comparable<Lesson>
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

    val lessonID:Int
        get() = periodNameMap[periodName]!!

    val lessonTime:String
        get() = periodTimeMap[periodName]!!

    companion object
    {
        val periodsADay = 10
        private val periodNameMap:Map<String,Int> = hashMapOf(Pair("AMReg",0), Pair("Tut",1), Pair("Assembly",1),
            Pair("Assembly",1), Pair("1",2), Pair("2",3), Pair("Break",4), Pair("3",5), Pair("4",6), Pair("Lunch",7),
            Pair("5",8),Pair("6",9), Pair("7",10))

        private val periodTimeMap:Map<String,String> = hashMapOf(Pair("AMReg","08:30-08:40"), Pair("Tut","08:40-08:50"), Pair("Assembly","08:40-08:50"),
            Pair("1","08:55-09:40"),
            Pair("2","09:45-10:"), Pair("Break","10:40-11:00"), Pair("3","11:05-11:55"), Pair("4","12:00-12:50"), Pair("Lunch","12:50-13:40"),Pair("PMReg","13:45-13:50"),
                Pair("5","13:50-14:40"),Pair("6","14:45-15:35"), Pair("7","15:40-16:30"))

        val periodNames: Set<String>
            get() = periodNameMap.keys

        fun findTimeRange(periodName: String) : String
        {
            return periodTimeMap[periodName]!!
        }
    }


    override fun compareTo(other: Lesson): Int
    {
        return (if(this.lessonID < other.lessonID) this.lessonID else other.lessonID)
    }


    override fun toString(): String
    {
        return "Lesson(year=$year, className=$className, pupilName=$pupilName, day=$day, periodName=$periodName, week=$week, groupInfo=$groupInfo, subData=$subData, classID='$classID', teachersInitials='$teachersInitials', roomID='$roomID')"
    }


}