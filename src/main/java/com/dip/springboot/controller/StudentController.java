package com.dip.springboot.controller;

import com.dip.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    // REST API with Request param
    ////htttp:localhost:8080/getstudent/query?id=1&fname=ramesh

    @GetMapping("getStudent/query")
    public  Student getStudentById(@RequestParam  int id,
                                   @RequestParam String fname){

        return  new Student(id,fname,"Das");
    }

    //REST API WITH POST REQUEST
@PostMapping("student/create")
@ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println("student = " + student.getFirstName());
        System.out.println("student = " + student.getLastName());

        return student;
    }
}
