package com.ssau.study.repository;

import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByName(String name);

    Student findById(long id);

    List<Student> findStudentsByGroup(Group group);

    void deleteById(long id);
}