package com.students.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.SQLite.helpers.StudentDatabaseHelper;
import com.students.R;
import com.students.contracts.StudentContract;

public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.students.R.layout.activity_create_student);
    }

    public void createStudent(View view) {
        StudentDatabaseHelper studentDatabaseHelper = new StudentDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = studentDatabaseHelper.getWritableDatabase();
        String surname = ((EditText)findViewById(R.id.txtSurname)).getText().toString();
        String name =  ((EditText)findViewById(R.id.txtName)).getText().toString();
        ContentValues values = new ContentValues();
        values.put(StudentContract.Student.COLUMN_NAME_STUDENT_ID, 1);
        values.put(StudentContract.Student.COLUMN_NAME_NAME, name );
        values.put(StudentContract.Student.COLUMN_NAME_SURNAME, surname);

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                StudentContract.Student.TABLE_NAME,
                "null",
                values);
        this.finish();
    }
}
