package com.students.contracts;

import android.provider.BaseColumns;

/**
 * Created by rjuarez on 4/28/16.
 */
public final class StudentContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public StudentContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Student implements BaseColumns {
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_STUDENT_ID = "studentId";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SURNAME = "surname";
        public static final String COLUMN_NAME_PHOTO = "photo";
    }
}
