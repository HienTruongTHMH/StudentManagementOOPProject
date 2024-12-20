/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

/**
 *
 * @author hientruongthmh
 */
public class schoolAdmin {
    private String adminID;
    private String adminName;
    private String adminGender;
    private String adminAuthor;

    public schoolAdmin() {
    }

    public schoolAdmin(String adminID, String adminName, String adminGender, String adminAuthor) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.adminGender = adminGender;
        this.adminAuthor = adminAuthor;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminAuthor() {
        return adminAuthor;
    }

    public void setAdminAuthor(String adminAuthor) {
        this.adminAuthor = adminAuthor;
    }
    
    
}
