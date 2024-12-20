/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.dao;

import demoOOPStudentManagement.database.JDBCUtils;
import demoOOPStudentManagement.model.uniClass;
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
public class ClassDAO implements DAOInterface<uniClass>{
    public static ClassDAO getInstance(){
        return new ClassDAO();
    }
    @Override
    public int add(uniClass t) {
        try {
            Connection ct = JDBCUtils.getConnection();

            Statement st = ct.createStatement();
            String querry = "USE studentManagerment;";
            st.execute(querry);

            String sql = "INSERT INTO ClassList(classID, className, major, classRoom, studentsCount)"
                    + "Values ('" + t.getClassID() + " ','" + t.getClassName() + "' ,'" + t.getMajor() +  " ','" + t.getRoom() + " '," + t.getStudentsCount() + ")";
            
            System.out.println("SuccessFull!!!" + sql);
            
            int result = st.executeUpdate(sql);
            
            JDBCUtils.closeConnection(ct);
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public int update(uniClass t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(uniClass t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<uniClass> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<uniClass> selectByCondition(String conditon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public uniClass selectByID(uniClass t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public uniClass selectByName(uniClass t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
