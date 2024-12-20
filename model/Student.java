/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

import java.util.Objects;

/**
 *
 * @author hientruongthmh
 */
public class Student {
    private String studentID;
    private String studentFullName;
    private String dateOfBirth;
    private String major;
    private byte gender;
    private String address;
    private String phoneNumber;
    private float scoreMidTerm, scoreFinal, scoreAverage;

    public Student(){}

    public Student(String studentFullName, String dateOfBirth, String major, byte gender, String address, String phoneNumber, float scoreMidTerm, float scoreFinal, float scoreAverage) {
        this.studentFullName = studentFullName;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.scoreMidTerm = scoreMidTerm;
        this.scoreFinal = scoreFinal;
        this.scoreAverage = scoreAverage;
    }
    
    public Student(String studentID, String studentFullName, String dateOfBirth, String major, byte gender,  String address, String phoneNumber, float scoreMidTerm, float scoreFinal, float scoreAverage) {
        this.studentID = studentID;
        this.studentFullName = studentFullName;
        this.major = major;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.scoreMidTerm = scoreMidTerm;
        this.scoreFinal = scoreFinal;
        this.scoreAverage = scoreAverage;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getScoreMidTerm() {
        return scoreMidTerm;
    }

    public void setScoreMidTerm(float scoreMidTerm) {
        this.scoreMidTerm = scoreMidTerm;
    }

    public float getScoreFinal() {
        return scoreFinal;
    }

    public void setScoreFinal(float scoreFinal) {
        this.scoreFinal = scoreFinal;
    }

    public float getScoreAverage() {
        return scoreAverage;
    }

    public void setScoreAverage(float scoreAverage) {
        this.scoreAverage = scoreAverage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (Float.floatToIntBits(this.scoreMidTerm) != Float.floatToIntBits(other.scoreMidTerm)) {
            return false;
        }
        if (Float.floatToIntBits(this.scoreFinal) != Float.floatToIntBits(other.scoreFinal)) {
            return false;
        }
        if (Float.floatToIntBits(this.scoreAverage) != Float.floatToIntBits(other.scoreAverage)) {
            return false;
        }
        if (!Objects.equals(this.studentFullName, other.studentFullName)) {
            return false;
        }
        return Objects.equals(this.major, other.major);
    }
    
    
            
    
    
}
