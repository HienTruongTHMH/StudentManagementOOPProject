/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.test;

import demoOOPStudentManagement.database.JDBCUtils;
import java.sql.Connection;

/**
 *
 * @author hientruongthmh
 */
public class testJDBCUtils {
    public static void main(String[] args) {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
