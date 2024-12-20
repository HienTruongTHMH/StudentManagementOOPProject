/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

/**
 *
 * @author hientruongthmh
 */
public class uniClass {
    private String classID;
    private String className;
    private String major;

    private String room;
    private int studentsCount;

    public uniClass(String classID, String className, String major,String room, int studentsCount) {
        this.classID = classID;
        this.className = className;
        this.major = major;
        this.room = room;
        this.studentsCount = studentsCount;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String toString() {
        return "uniClass{" + "classID=" + classID + ", className=" + className + ", major=" + major + ", room=" + room + ", studentsCount=" + studentsCount + '}';
    }
    
    
}
