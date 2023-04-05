package com.ssau.study.controller;

import com.ssau.study.annotations.IsAdmin;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentPojo> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{name}")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentPojo> findAllByName(@PathVariable String name) {
        return studentService.findAllByName(name);
    }

    @DeleteMapping("/{pk}")
    @Secured("ROLE_ADMIN")
    public boolean delete(@PathVariable long pk) {
        return studentService.deleteById(pk);
    }

    @PostMapping
    @IsAdmin
    public StudentPojo createStudent(@Valid @RequestBody StudentPojo student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{pk}")
    @IsAdmin
    public StudentPojo updateStudent(@PathVariable long pk, @Valid @RequestBody StudentPojo student) {
        return studentService.updateStudent(pk, student);
    }
}

