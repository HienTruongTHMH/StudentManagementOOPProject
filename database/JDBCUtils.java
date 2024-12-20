/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author hientruongthmh
 */
public class JDBCUtils {
    public static Connection getConnection(){
        Connection ct = null;
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mySQL://localhost:3306";
            String username = "root";
            String password = "";
            
            ct = DriverManager.getConnection(url, username, password);
            System.out.println("Connection thành công.");
            System.out.println(ct.getCatalog());           
        }catch(SQLException e){
            e.printStackTrace();
        }
            
        return ct;
    }
    
    public static void closeConnection(Connection ct){
        try {
            if(ct != null){
                ct.close();
            }
        } catch (Exception e) {
        }
    }
    
    public static void printInfo(Connection ct){
        try {
            if(ct != null){
                DatabaseMetaData metaDT = ct.getMetaData();
                System.out.println(metaDT.getDatabaseProductName());
            }
        } catch (Exception e) {
        }
    }
}
