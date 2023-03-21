package com.ssau.study.dto;

import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GroupPojo {
    private long id;
    private String name;
    private List<StudentPojo> students;

    public static GroupPojo fromEntity(Group group) {
        GroupPojo pojo = new GroupPojo();
        pojo.setId(group.getId());
        pojo.setName(group.getName());

        if (group.getStudents() != null) {
            List<StudentPojo> students = new ArrayList<>();
            pojo.setStudents(students);
            for (Student student : group.getStudents()) {
                students.add(StudentPojo.fromEntity(student));
            }
        }
        return pojo;
    }

    public static Group toEntity(GroupPojo pojo) {
        Group group = new Group();
        group.setId(pojo.getId());
        group.setName(pojo.getName());

        if (pojo.getStudents() != null) {
            List<Student> students = new ArrayList<>();
            group.setStudents(students);
            for (StudentPojo studentPojo : pojo.getStudents()) {
                Student student = StudentPojo.toEntity(studentPojo);
                student.setGroup(group);
                students.add(student);
            }
        }
        return group;
    }

    public static List<GroupPojo> convertGroupsToPojo(List<Group> groups) {
        List<GroupPojo> pojoList = new ArrayList<>();
        for (Group group : groups) {
            pojoList.add(GroupPojo.fromEntity(group));
        }
        return pojoList;
    }
}
