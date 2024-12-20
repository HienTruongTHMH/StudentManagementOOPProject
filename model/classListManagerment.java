/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoOOPStudentManagement.model;

import java.util.ArrayList;

/**
 *
 * @author hientruongthmh
 */
public class classListManagerment {
    private ArrayList<uniClass> classList;
    
    public classListManagerment(){
        this.classList = new ArrayList<uniClass>();
    }

    public classListManagerment(ArrayList<uniClass> classList) {
        this.classList = classList;
    }

    public ArrayList<uniClass> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<uniClass> classList) {
        this.classList = classList;
    }
    
    public void insert(uniClass c){
        classList.add(c);
    }
    
    public static void main(String[] args) {
        classListManagerment cList = new classListManagerment();
        
        
        
    }
     
}
