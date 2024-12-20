/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.dao;

import demoOOPStudentManagement.database.JDBCUtils;
import demoOOPStudentManagement.model.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hientruongthmh
 */
public class StudentDAO implements DAOInterface<Student>{
    
    public static StudentDAO getInstance(){
        return new StudentDAO();
    }

    @Override
    public int add(Student t) {
        try {
            Connection ct = JDBCUtils.getConnection();

            Statement st = ct.createStatement();
            String querry = "USE studentManagerment;";
            st.execute(querry);

            String sql = "INSERT INTO Student(studentID, fullName, major, gender, dateOfBirth, address, phoneNumber) " 
            + "VALUES ('" + t.getStudentID() + "', '" + t.getStudentFullName() + "', '" 
            + t.getMajor() + "', " + t.getGender() + ", '" + t.getDateOfBirth() 
            + "', '" + t.getAddress() + "', '" + t.getPhoneNumber() + "')";
            
            System.out.println("SuccessFull!!!" + sql);
            
            int result = st.executeUpdate(sql);
            
            JDBCUtils.closeConnection(ct);
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public int update(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> selectByCondition(String conditon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student selectByID(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student selectByName(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
