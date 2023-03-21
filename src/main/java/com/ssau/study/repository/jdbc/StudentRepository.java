package com.ssau.study.repository.jdbc;

import com.ssau.study.entity.Student;

import java.util.Date;
import java.util.List;

public interface StudentRepository {
    int count();

    List<Student> findAll();

    List<Student> findAllByName(String name);

    List<Student> findByID(long id);

    void deleteById(long id);

    List<Student> createStudent(String name, Date birthdate, int number);

    int updateStudent(long id, String name, Date birthdate, int number);
}
