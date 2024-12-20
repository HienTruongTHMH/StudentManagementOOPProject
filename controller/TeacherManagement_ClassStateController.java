/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.controller;

import demoOOPStudentManagement.view.TeacherManagementStateUI;
import demoOOPStudentManagement.view.TeacherManagement_ClassStateUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author hientruongthmh
 */
public class TeacherManagement_ClassStateController implements ActionListener{
    public TeacherManagement_ClassStateUI teacherClassView;
    public TeacherManagementStateUI teacherManageView;

    public TeacherManagement_ClassStateController(TeacherManagementStateUI teacherManageView) {
        this.teacherManageView = teacherManageView;
    }

    public TeacherManagement_ClassStateController(TeacherManagement_ClassStateUI teacherClassUI) {
        this.teacherClassView = teacherClassUI;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        JOptionPane.showMessageDialog(teacherClassView, "Bạn vừa nhấn vào: " + button);
    }
    
    
    
}
