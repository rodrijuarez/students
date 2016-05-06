package com.students.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.SQLite.helpers.StudentDatabaseHelper;
import com.students.R;
import com.students.adapters.StudentAdapter;
import com.students.contracts.StudentContract;
import com.students.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.students_list);

        this.updateStudentsList();

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                Student itemValue = (Student) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue.getName(), Toast.LENGTH_LONG)
                        .show();

            }

        });

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(StudentListActivity.this, CreateStudentActivity.class);
                startActivityForResult(k, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.updateStudentsList();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateStudentsList() {
        StudentDatabaseHelper studentDatabaseHelper = new StudentDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = studentDatabaseHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                StudentContract.Student._ID,
                StudentContract.Student.COLUMN_NAME_NAME,
                StudentContract.Student.COLUMN_NAME_SURNAME,
                StudentContract.Student.COLUMN_NAME_PHOTO,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                StudentContract.Student.COLUMN_NAME_NAME+ " DESC";

        Cursor cursor = db.query(
                StudentContract.Student.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        cursor.moveToFirst();

        List<Student> students=  new ArrayList<Student>();

        while(!cursor.isAfterLast()) {
            System.out.println("estoy entrando aca");
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(StudentContract.Student._ID)
            );
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(StudentContract.Student.COLUMN_NAME_NAME)
            );
            String surname = cursor.getString(
                    cursor.getColumnIndexOrThrow(StudentContract.Student.COLUMN_NAME_SURNAME)
            );
            byte[] studentPhoto = cursor.getBlob(
                    cursor.getColumnIndexOrThrow(StudentContract.Student.COLUMN_NAME_PHOTO)
            );
            Student student = new Student(name, surname, studentPhoto);
            students.add(student);
            cursor.moveToNext();
        }

        System.out.println("students");
        System.out.println(students);

        ArrayAdapter<Student> adapter = new StudentAdapter(this,
                R.layout.student_list, students);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }
}
