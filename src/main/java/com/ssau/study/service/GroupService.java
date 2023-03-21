package com.ssau.study.service;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import com.ssau.study.repository.GroupRepository;
import com.ssau.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class GroupService {
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<GroupPojo> findAll() {
        List<Group> groups = groupRepository.findBy();
        return GroupPojo.convertGroupsToPojo(groups);
    }

    public List<GroupPojo> findAllByName(@PathVariable String name) {
        List<Group> groups = groupRepository.findAllByName(name);
        return GroupPojo.convertGroupsToPojo(groups);
    }

    public GroupPojo findById(long pk) {
        return GroupPojo.fromEntity(groupRepository.findById(pk));
    }

    public List<StudentPojo> findStudentsByGroup(long groupId) {
        Group group = groupRepository.findById(groupId);
        List<Student> students = studentRepository.findStudentsByGroup(group);
        return StudentPojo.convertStudentsToPojo(students);
    }

    public void deleteById(long pk) {
        groupRepository.deleteById(pk);
    }

    public GroupPojo createGroup(GroupPojo pojo) {
        groupRepository.save(GroupPojo.toEntity(pojo));
        return pojo;
    }

    public StudentPojo createStudent(long groupId, StudentPojo pojo) {
        Student student = StudentPojo.toEntity(pojo);
        student.setGroup(groupRepository.findById(groupId));
        studentRepository.save(student);
        return StudentPojo.fromEntity(student);
    }

    public void updateGroup(long pk, GroupPojo pojo) {
        Group group = groupRepository.findById(pk);
        if (group != null) {
            group.setName(pojo.getName());
            groupRepository.save(group);
        }
    }

    public void updateStudent(long groupId, long studentId, StudentPojo pojo) {
        Student student = studentRepository.findById(studentId);
        if (student != null) {
            student.setName(pojo.getName());
            student.setBirthdate(pojo.getBirthdate());
            student.setNumber(pojo.getNumber());
            student.setGroup(groupRepository.findById(groupId));
            studentRepository.save(student);
        }
    }
}