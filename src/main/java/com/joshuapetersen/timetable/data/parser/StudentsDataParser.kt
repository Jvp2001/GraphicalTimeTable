//package com.joshuapetersen.timetable.data.parser
//
//import org.apache.poi.ss.usermodel.Row
//import org.apache.poi.ss.usermodel.WorkbookFactory
//import org.apache.poi.xssf.usermodel.XSSFWorkbook
//import java.io.File
//import java.io.FileInputStream
//import kotlin.reflect.KClass
//
//object StudentsDataParser : FileParser
//{
//    override fun <T : Any> parseFile(file: String, clazz: KClass<T>)
//    {
//        return parseFile(File(file), clazz)
//    }
//
//    override fun <T : Any> parseFile(file: File, clazz: KClass<T>) = {return null}}
//
////    {
////        if (!file.path.endsWith(".xlxs") || !file.path.endsWith(".xls"))
////            throw Exception("File has to be an Excel workbook of type '.xls' or '.xlsx'!")
//
////        val workbook = WorkbookFactory.create(FileInputStream(file))
////        println(workbook.numberOfSheets)
////        var columnHeaderRow: Row? = null
////        for (sheet in workbook.sheetIterator())
////        {
////            if(columnHeaderRow == null)
////            {
////            }
////            for (row in sheet.rowIterator().withIndex())
////            {
////
////                for (cell in row.value.cellIterator())
////                {
////                }
////
////            }
////        }
////
////    }
////}
