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
        uniClass c1 = new uniClass("C01","Công Nghệ Thông Tin", "Lập Trình Java", (int)30);
        uniClass c2 = new uniClass("C02","Toán Học", "Giải Tích 1", 25);
        uniClass c3 = new uniClass("C03","Ngôn Ngữ", "Tiếng Anh Cơ Bản", 40);
        
        
    }
     
}
