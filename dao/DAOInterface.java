/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package demoOOPStudentManagement.dao;

import java.util.ArrayList;

/**
 *
 * @author hientruongthmh
 */
public interface DAOInterface<T> {
    public int add(T t);
    
    public int update(T t);
    
    public int delete(T t);
    
    public ArrayList<T> selectAll();
    
    public ArrayList<T> selectByCondition(String conditon);
    
    public T selectByID(T t);
    
    public T selectByName(T t);
}
