package com.students.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.students.R;
import com.students.models.Student;

import java.util.List;


/**
 * Created by rjuarez on 4/28/16.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    private Activity activity;
    private List<Student> lStudent;
    private static LayoutInflater inflater = null;

    public StudentAdapter (Activity activity, int textViewResourceId, List<Student> _lStudent) {
        super(activity, textViewResourceId, _lStudent);
        try {
            this.activity = activity;
            this.lStudent = _lStudent;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return lStudent.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_surname;
        public ImageView display_photo;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.student_list, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.studentName);
                holder.display_surname = (TextView) vi.findViewById(R.id.studentSurname);
                holder.display_photo = (ImageView) vi.findViewById(R.id.studentPhotoTwo);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_name.setText(lStudent.get(position).getName());
            holder.display_surname.setText(lStudent.get(position).getSurname());
            holder.display_photo.setImageBitmap(BitmapFactory.decodeByteArray(lStudent.get(position).getPhoto(), 0, lStudent.get(position).getPhoto().length));


        } catch (Exception e) {


        }
        return vi;
    }
}