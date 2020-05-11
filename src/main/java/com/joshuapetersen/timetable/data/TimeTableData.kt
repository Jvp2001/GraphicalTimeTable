package com.joshuapetersen.timetable.data

import com.beust.klaxon.*
import com.joshuapetersen.timetable.Utils.ExcelFileFilter
import com.joshuapetersen.timetable.Utils.LOG
import com.joshuapetersen.timetable.Utils.Utils
import com.poiji.bind.Poiji
import com.poiji.option.PoijiOptions
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileReader
import java.io.FilenameFilter
import java.nio.file.Paths
import com.joshuapetersen.timetable.data.ImageReference as ImageReference

private data class StudentJsonFile(val fileName: String, val data: JSONObject)
private data class WorkbookInfo(var lessons: MutableList<MutableList<Lesson>>? = null, val file: File?)
{
    init
    {
    }

    val workbook = XSSFWorkbook(file)
    val path: String = file!!.absolutePath
    val numberOfSheets = workbook.numberOfSheets
}

data class StudentInfo(
    @Json(name = "pupil_name")
    val name: String,
    val year: Int,
    @Json(name = "class_name")
    val className: String,
    val lessons: Array<StudentLesson>
)

data class StudentLesson(
    val week: Int, @Json(name = "period_name") val name: String,
    val day: String, @Json(name = "group_info") val groupInfo: GroupInfo
)

data class GroupInfo(
    @Json(name = "room_id")
    val roomID: String,
    @Json(name = "class_id")
    val classID: String,
    @Json(name = "lesson_time")
    val lessonTime: String,
    @Json(name = "teacher_initials")
    val teacherInitials: String
)

data class ImageReference(
    @Json(name = "subject_id")
    val subjectID: String,
    @Json(name = "subject_name")
    val subjectName: String,
    @Json(name = "image_path")
    var imagePath: String = ""
)


object TimeTableData
{
    private val daysMap: HashMap<String, MutableList<StudentLesson>> = HashMap()
    private val studentName: String? = null
    private var imagesReferences: List<ImageReference>? = null

    var studentLessons: MutableList<MutableList<Lesson>>? = null
    var student: StudentInfo? = null
    private val workbooks: Array<WorkbookInfo>
        get()
        {
            val f = File(Utils.assetsDir, "/data/excel")

            val files = f.listFiles(ExcelFileFilter())
            val workbooks = mutableListOf<WorkbookInfo>()
            for (file in files)
            {
                LOG(file)
                workbooks.add(WorkbookInfo(null, file))
            }
            LOG(workbooks.size)
            return workbooks.toTypedArray()
        }

    private fun parseWorkbook(): MutableList<MutableList<Lesson>>?
    {
        var data: MutableList<MutableList<Lesson>>? = arrayListOf()
        val options = PoijiOptions.PoijiOptionsBuilder.settings().limit(49).build()
        LOG(workbooks!![0] == null)
        var workbook: Workbook = workbooks[0].workbook
        for (i in 0..workbook.numberOfSheets)
        {
            val options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(i).limit(49).build()

            val datum = Poiji.fromExcel(

                workbooks[0].file,

                Lesson::class.java,
                options

            )

            data?.add(datum)
        }



        return data
    }

    private val klaxon = Klaxon()

    private fun readData()
    {
        imagesReferences =
            klaxon.parseArray<ImageReference>(FileReader(Utils.assetsDir + "/data/json/images.json"))

        val jsonDataFolder = File(Utils.assetsDir, "data/json/worksheets")
        //TODO: change to check to see if the number of json files is equal to the number of workbook pages.
        if (jsonDataFolder.listFiles()?.size!! == workbooks[0].numberOfSheets)
        {
            //TODO: Deserialize json
            student = Klaxon().parse<StudentInfo>(FileReader(jsonDataFolder.listFiles()[0]))
        }
//6
        else
        {
            studentLessons = parseWorkbook()
            val toJson = toJson()
            for (jsonObject in toJson)
            {
                val file = File(jsonDataFolder, jsonObject.fileName.replace(", ", "").replace(" ", "") + ".json")

                LOG("FilePath:" + file.absolutePath)
                if (!file.exists())
                {
                    file.createNewFile()
                } else
                {
                    file.writer().use {
                        it.write(jsonObject.data.toString(1))
                    }
                }
            }
            student = Klaxon().parse<StudentInfo>(workbooks[0].file!!)


        }

    }


    fun findSubjectImagePath(id: String): String?
    {
        val imageDir = Paths.get(Utils.imagesDir, "subjects").toFile()
        var imagePath = "file:///"
        val images = imageDir.listFiles()
        if (id.toLowerCase() == "ac")
            imagePath += imageDir.listFiles({ dir, name -> name.toLowerCase().startsWith("assembly") })[0].toPath().toString()
        else
            for (image in images)
            {
                if (image.nameWithoutExtension.contains(id, true))
                    imagePath += image.path
            }
        return imagePath
    }

    fun findLessonForDay(day: String, studentIndex: Int = 0): ArrayList<Lesson>
    {
        val lessons: ArrayList<Lesson> = arrayListOf()
        for (lesson in this!!.studentLessons!![studentIndex])
        {
            if (lesson.day.equals(day, ignoreCase = true))
            {
                lessons.add(lesson)
            }
        }
//            lessons.sort()
        return lessons
    }

    fun findLessonForDay(day: String, studentIndex: Int = 0, periodName: String = "1"): Lesson?
    {
        val lessons: ArrayList<Lesson> = arrayListOf()
        for (lesson in this!!.studentLessons!![studentIndex])
        {
            if (lesson.day.equals(day, ignoreCase = true) && lesson.periodName == periodName)
            {
                return lesson;
            }
        }
//            lessons.sort()
        return null
    }

    private fun toJson(): ArrayList<StudentJsonFile>
    {

        val jsonObjects = arrayListOf<StudentJsonFile>()


        studentLessons?.iterator()!!.forEach { worksheet ->
            worksheet.forEach { lesson ->
                var lessons: JSONArray = JSONArray()
                var storedCommonInfo: Boolean = false
                var root = JSONObject()
                worksheet.forEach { lesson ->
                    if (!storedCommonInfo)
                    {
                        root.put("year", lesson.year)
                        root.put("pupil_name", lesson.pupilName)
                        root.put("class_name", lesson.tutorID)
                        storedCommonInfo = true
                    }
                    //TODO: Finish JSON Data Building.
                    else
                    {
                        val lessonObject = JSONObject()
                        lessonObject.put("period_name", lesson.periodName)
                        lessonObject.put("week", lesson.week)
                        lessonObject.put("day", lesson.day)
                        val groupInfoObject = JSONObject()
                        groupInfoObject.put("teacher_initials", lesson.teachersInitials)
                        groupInfoObject.put("class_id", lesson.classID)
                        //                        groupInfoObject.put("lesson_id", lesson.lessonID)
                        groupInfoObject.put("room_id", lesson.roomID)
                        groupInfoObject.put("lesson_time", lesson.lessonTime)
                        lessonObject.put("group_info", groupInfoObject)
                        lessons.put(lessonObject)
                    }
                }


                root.put("lessons", lessons)
                LOG("Root: ")
                jsonObjects.add(StudentJsonFile(root["pupil_name"].toString(), root))
            }
        }

        return jsonObjects

    }

    init
    {

        val subjectReferencesJsonFile = File(Utils.assetsDir, "data/json/subject_references.json")
        val subjectsJsonFile = File(Utils.assetsDir, "data/json/subjects.json")
        var subjects: List<String> = mutableListOf()

        subjects = klaxon.parseArray(subjectsJsonFile)!!
        val irs = mutableListOf<ImageReference>()

        for (subject in subjects)
        {
            var imageReference: ImageReference? = null
            if (subject.toLowerCase() == "assembly") imageReference =
                ImageReference("ac", subject) else imageReference =
                ImageReference(subject.substring(0, 2).toLowerCase(), subject)
            irs += imageReference
        }
        imagesReferences = irs

        subjectReferencesJsonFile.createNewFile()
        val jsonString = klaxon.toJsonString(imagesReferences)
        subjectReferencesJsonFile.printWriter().use { it.write(jsonString) }



        readData()
        //TODO: Change index later
        //studentName = studentLessons!![0].get(0).pupilName!!
        for (day in Utils.DAYS)
        {
            addItem(day, student!!.lessons)
        }

    }

    operator fun get(key: String): MutableList<StudentLesson>? = daysMap[key]

    private fun addItem(keyName: String, lessons: Array<StudentLesson>)
    {
        val specificLessons = ArrayList<StudentLesson>(10)
        for (lesson in lessons)
        {
            if (keyName == lesson.day)
            {
                specificLessons.add(lesson)
            }
        }
        daysMap.put(keyName, specificLessons)
    }


}

