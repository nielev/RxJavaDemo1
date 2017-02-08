package com.nielev.rxjavademo1.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nielev.rxjavademo1.R;
import com.nielev.rxjavademo1.entity.Course;
import com.nielev.rxjavademo1.entity.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class LogicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);
        Button btn = (Button) findViewById(R.id.btn_1);
        Button btn2 = (Button) findViewById(R.id.btn_2);
        final Student[] students = getStudents();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStudentName(students);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourseName(students);
            }
        });
    }

    /**
     * 获取每个学生对应的课程
     * @param students
     */
    private void getCourseName(final Student[] students) {
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {
                Toast.makeText(LogicActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.i("STU_COURSE",course.getName());
            }
        };

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                //将Student对象转换成Course对象
                return Observable.from(student.getCourses());
            }
        }).subscribe(subscriber);
    }

    /**
     * 获取学生名
     * @param students
     */
    private void getStudentName(Student[] students) {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(LogicActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.i("STUDENT",s);
            }
        };
        Observable.from(students).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                //Student -> String
                return student.getName();
            }
        }).subscribe(subscriber);
    }

    @NonNull
    private Student[] getStudents() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("语文"));
        courses.add(new Course("数学"));
        courses.add(new Course("英语"));
        Student student1 = new Student("学生a",courses);
        courses = new ArrayList<>();
        courses.add(new Course("化学"));
        courses.add(new Course("物理"));
        courses.add(new Course("地理"));
        Student student2 = new Student("学生b",courses);
        courses = new ArrayList<>();
        courses.add(new Course("历史"));
        courses.add(new Course("政治"));
        courses.add(new Course("美术"));
        Student student3 = new Student("学生c",courses);
        return new Student[]{student1,student2,student3};
    }
}
