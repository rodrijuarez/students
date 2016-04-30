package com.SQLite.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.students.contracts.StudentContract;

/**
 * Created by rjuarez on 4/28/16.
 */
public class StudentDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Students_test.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentContract.Student.TABLE_NAME + " (" +
                    StudentContract.Student._ID + " INTEGER PRIMARY KEY," +
                    StudentContract.Student.COLUMN_NAME_STUDENT_ID + TEXT_TYPE + COMMA_SEP +
                    StudentContract.Student.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    StudentContract.Student.COLUMN_NAME_SURNAME + TEXT_TYPE + COMMA_SEP +
                    StudentContract.Student.COLUMN_NAME_PHOTO + BLOB_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentContract.Student.TABLE_NAME;

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
