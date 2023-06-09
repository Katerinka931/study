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
    private long group_id;

    public static StudentPojo fromEntity(Student student) {
        StudentPojo pojo = new StudentPojo();
        setPojoData(pojo, student);
        return pojo;
    }

    public static StudentPojo fromEntity(Student student, long group_id) {
        StudentPojo pojo = new StudentPojo();
        setPojoData(pojo, student);
        pojo.setGroup_id(group_id);
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

    private static void setPojoData(StudentPojo pojo, Student student) {
        pojo.setId(student.getId());
        pojo.setName(student.getName());
        pojo.setBirthdate(student.getBirthdate());
        pojo.setNumber(student.getNumber());
    }

    public static List<StudentPojo> convertStudentsToPojo(List<Student> students) {
        List<StudentPojo> pojoList = new ArrayList<>();
        for (Student student : students) {
            pojoList.add(StudentPojo.fromEntity(student));
        }
        return pojoList;
    }

    public static List<StudentPojo> convertToPojoListWithGroups(List<Student> students){
        List<StudentPojo> pojoList = new ArrayList<>();
        for (Student student : students) {
            long id = 0;
            if (student.getGroup() != null)
                id = student.getGroup().getId();
            pojoList.add(StudentPojo.fromEntity(student, id));
        }
        return pojoList;
    }
}
