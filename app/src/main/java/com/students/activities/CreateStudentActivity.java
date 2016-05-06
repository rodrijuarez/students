package com.students.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.SQLite.helpers.StudentDatabaseHelper;
import com.students.R;
import com.students.contracts.StudentContract;

import java.io.ByteArrayOutputStream;

public class CreateStudentActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap studentPhoto;

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
        values.put(StudentContract.Student.COLUMN_NAME_PHOTO, getBitmapAsByteArray(this.studentPhoto));

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                StudentContract.Student.TABLE_NAME,
                "null",
                values);

        setResult(1); //add this
        finish();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.studentPhoto);
            mImageView.setImageBitmap(imageBitmap);
            this.studentPhoto = imageBitmap;
        }
    }
}
