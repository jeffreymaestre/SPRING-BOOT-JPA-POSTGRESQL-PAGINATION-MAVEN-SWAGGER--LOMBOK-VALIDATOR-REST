package com.example.CRUD.controller;

import com.example.CRUD.model.Student;
import com.example.CRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination){
        return ok(studentService.getAllStudent(page,size,enablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(studentService.existById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable("id") Long id){
        studentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.editStudent(student));
    }

}
