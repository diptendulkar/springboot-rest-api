package com.dip.springboot.controller;

import com.dip.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("school")
public class StudentController {
@GetMapping("student")
    public Student student(){
        Student student = new Student(1,"Ramesh","Das");
        return student;
    }

    @GetMapping("getStudent")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Ramesh","Das");
        return  ResponseEntity.ok(student);
    }

    @GetMapping("getStudentList")
    public ResponseEntity<List<Student>> getStudentList(){

    List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Ramesh","Das"));
        studentList.add(new Student(5,"Rana","Singh"));
        studentList.add(new Student(2,"Disa","roy"));
        studentList.add(new Student(3,"Rani","dutta"));
        studentList.add(new Student(4,"Rupesh","Bora"));


    return  ResponseEntity.ok(studentList);
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


@PutMapping("student/{id}/update")
@ResponseStatus(HttpStatus.OK)
public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentid){
    System.out.println(student.getId());
    System.out.println("student = " + student.getFirstName());
    System.out.println("student = " + student.getLastName());

    return student;
}

    @DeleteMapping("student/{id}/delete")
//    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable("id") int studentid){

        System.out.println("Delete student = " + studentid);


        return "student Deleted sucessfully!!";
    }
}
