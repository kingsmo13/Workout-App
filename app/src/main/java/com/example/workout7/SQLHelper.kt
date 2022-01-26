package com.example.workout7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.prefs.PreferencesFactory

class SQLHelper(context : Context , factory : SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME ,factory , DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "Workout7"
        private const val TABLE_NAME = "History"
        private const val DATABASE_VERSION = 1

        private const val ID = "id"
        private const val DATE = "dateCol"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val Create_Query = ("CREATE TABLE " + TABLE_NAME + "( " + ID+
                 " INTEGER PRIMARY KEY ," + DATE +
                " TEXT )")

        db?.execSQL(Create_Query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        db?.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME)
        onCreate(db)
    }

    fun addData(Date : String)
    {
        val db = this.writableDatabase
        val values : ContentValues = ContentValues()
        values.put(DATE , Date)
        db.insert(TABLE_NAME , null , values)
        db.close()
    }

    fun getAllDates() : ArrayList<String>
    {
        val list = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)

        while(cursor.moveToNext())
        {
            val dt = cursor.getString(cursor.getColumnIndex(DATE))
            list.add(dt)
        }
        return list
    }
}