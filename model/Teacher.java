/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

/**
 *
 * @author hientruongthmh
 */
public class Teacher {
    private String teacherID;
    private String teacherFullName;
    private String dateOfBirth;
    private byte gender;
    private String major;
    private String salary;

    public Teacher(String teacherID, String teacherFullName, String dateOfBirth, byte gender, String major, String salary) {
        this.teacherID = teacherID;
        this.teacherFullName = teacherFullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.major = major;
        this.salary = salary;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    
}
