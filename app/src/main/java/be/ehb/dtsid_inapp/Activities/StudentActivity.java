package be.ehb.dtsid_inapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.StudentRegistration;
import be.ehb.dtsid_inapp.TeacherFragments.Options;
import be.ehb.dtsid_inapp.TeacherFragments.TeacherLogin;

public class StudentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }

    public void goToNextFragment(View v) {
        Button goToButton = (Button) v;

        if (goToButton.getId() == R.id.btn_student_registreren) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new StudentRegistration())
                    .commit();
        }
        /*else if (goToButton.getId() == R.id.btn_teacher_login) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new Options())
                    .commit();
        } */
        /*else if (goToButton.getId() == R.id.btn_student_registreren) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new StudentRegistration())
                    .commit();
        }*/
    }
}
