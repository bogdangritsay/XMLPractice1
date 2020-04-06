package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Student {
   private String firstName;
   private String lastName;
   private String groupNumber;
   private ArrayList<Subject> subjects;
   private float avg;

   public static boolean compareAVG(float num1, float num2) {

       if(Float.toString(num1).length() > 3) {
           num1 = new BigDecimal(num1).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
       } if(Float.toString(num2).length() > 3) {
           num2 = new BigDecimal(num2).setScale(1, BigDecimal.ROUND_DOWN).floatValue();
       }
       if(num1 == num2) return true;
       else return false;
   }

   public float calculateAverage() {
       float avg;
       float sum = 0;
       for (Subject subject : subjects) {
           sum+= subject.getMark();
       }
       return  sum/subjects.size();
   }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public Student() {}

    public Student(String firstName, String lastName, String groupNumber, ArrayList<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
        this.subjects = subjects;
        if(this.subjects != null ) {
            this.avg = calculateAverage();
        }
    }

    public Student(String firstName, String lastName, String groupNumber, ArrayList<Subject> subjects, float avg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
        this.subjects = subjects;
        this.avg = avg;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", subjects=" + subjects +
                ", average= " + avg +
                 '}';
    }
}
