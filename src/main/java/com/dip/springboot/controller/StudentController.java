package com.dip.springboot.controller;

import com.dip.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
@GetMapping("student")
    public Student getStudent(){
        Student student = new Student(1,"Ramesh","Das");
        return student;
    }

    @GetMapping("getStudentList")
    public List<Student> getStudentList(){

    List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Ramesh","Das"));
        studentList.add(new Student(5,"Rana","Singh"));
        studentList.add(new Student(2,"Disa","roy"));
        studentList.add(new Student(3,"Rani","dutta"));
        studentList.add(new Student(4,"Rupesh","Bora"));


    return  studentList;
    }

    // Rest API Path Variable
@GetMapping("getStudent/{id}")
    public  Student getStudent(@PathVariable("id")  int studentId){

    return  new Student(studentId,"Ramesh","Das");
    }
}
