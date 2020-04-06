package org.example.model;

import java.util.ArrayList;

public class Group {
    private ArrayList<Student> studentList;

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public Group(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public Group() {}

    @Override
    public String toString() {
        return "Group{" +
                "studentList=" + studentList +
                '}';
    }
}
