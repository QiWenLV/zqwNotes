package com.zqw.principle.demeter.improve;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪米特法则 (遵守)
 */
public class Demeter2 {

    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        //输出学院的员工id 和总部员工id
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

// 学校总部员工类
class Employee {
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

// 学院员工类
class CollegeEmployee {
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

//管理学院员工的管理类
class CollegeManager {
    //返回学院的员工
    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工 = " + i);
            list.add(emp);
        }
        return list;
    }

    //输出学院员工的信息
    public void printEmployee() {
        List<CollegeEmployee> list1 = getAllEmployee();
        System.out.println("-----------------------学院员工");
        list1.stream().map(CollegeEmployee::getId).forEach(System.out::println);
    }
}

//管理总部员工的管理类
//SchoolManager 的直接朋友：Employee、CollegeManager
// CollegeEmployee类不是直接朋友，违背了迪米特法则
class SchoolManager{
    //返回学校总部的员工
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Employee emp = new Employee();
            emp.setId("总部员工id = " + i);
            list.add(emp);
        }
        return list;
    }

    //输出方法
    public void printAllEmployee(CollegeManager sub) {
        List<Employee> list2 = this.getAllEmployee();
        System.out.println("-----------------------总部员工");
        list2.stream().map(Employee::getId).forEach(System.out::println);
    }
}



