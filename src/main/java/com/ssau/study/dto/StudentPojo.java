package com.ssau.study.dto;

import com.ssau.study.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class StudentPojo {
    private long id;
    private String name;
    private Date birthdate;
    private int number;
    private int group_id;

    public static StudentPojo fromEntity(Student student) {
        StudentPojo pojo = new StudentPojo();
        pojo.setId(student.getId());
        pojo.setName(student.getName());
        pojo.setBirthdate(student.getBirthdate());
        pojo.setNumber(student.getNumber());
        return pojo;
    }

    public static Student toEntity(StudentPojo pojo) {
        Student student = new Student();
        student.setId(pojo.getId());
        student.setName(pojo.getName());
        student.setBirthdate(pojo.getBirthdate());
        student.setNumber(pojo.getNumber());
        return student;
    }

    public static List<StudentPojo> convertStudentsToPojo(List<Student> students) {
        List<StudentPojo> pojoList = new ArrayList<>();
        for (Student student : students) {
            pojoList.add(StudentPojo.fromEntity(student));
        }
        return pojoList;
    }
}
