package com.joshuapetersen.timetable.Utils

import java.io.File
import java.io.FilenameFilter

class ExcelFileFilter : FilenameFilter
{
    override fun accept(dir: File?, name: String?): Boolean
    {
        return name!!.endsWith(".xlsx") && !name.startsWith("~$")
    }
}
