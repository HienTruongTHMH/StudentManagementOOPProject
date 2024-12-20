/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

import java.util.ArrayList;

/**
 *
 * @author hientruongthmh
 */
public class studentListManagerment {
    private ArrayList<Student> studentList;

    public studentListManagerment(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    
    public void insert(Student s){
        this.studentList.add(s);
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    
}
