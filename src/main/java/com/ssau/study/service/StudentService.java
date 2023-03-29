package com.ssau.study.service;

import com.ssau.study.dto.StudentPojo;
import com.ssau.study.entity.Student;
import com.ssau.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequiredArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentPojo> findAll() {
        List<Student> students = studentRepository.findAll();
        return StudentPojo.convertStudentsToPojo(students);
    }

    public List<StudentPojo> findAllByName(@PathVariable String name) {
        List<Student> students = studentRepository.findAllByName(name);
        return StudentPojo.convertStudentsToPojo(students);
    }

    public StudentPojo findById(long pk) {
        return StudentPojo.fromEntity(studentRepository.findById(pk));
    }

    public boolean deleteById(long pk) {
        if (studentRepository.findById(pk) == null)
            return false;

        studentRepository.deleteById(pk);
        return true;
    }

    public StudentPojo createStudent(StudentPojo pojo) {
        try {
            studentRepository.save(StudentPojo.toEntity(pojo));
            return pojo;
        } catch (Exception e) {
            return null;
        }
    }

    public StudentPojo updateStudent(long pk, StudentPojo pojo) {
        Student student = studentRepository.findById(pk);
        if (student != null) {
            student.setName(pojo.getName());
            student.setNumber(pojo.getNumber());
            student.setBirthdate(pojo.getBirthdate());
            studentRepository.save(student);
            return StudentPojo.fromEntity(student);
        }
        return pojo;
    }
}