package com.ssau.study.controller;

import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.GroupService;
import com.ssau.study.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<GroupPojo> findAll() {
        return groupService.findAll();
    }

    @GetMapping("/{name}")
    public List<GroupPojo> findAllByName(@PathVariable String name) {
        return groupService.findAllByName(name);
    }

    @GetMapping("/{groupId}/students")
    public List<StudentPojo> findAllStudents(@PathVariable long groupId) {
        return groupService.findStudentsByGroup(groupId);
    }

    @DeleteMapping("/{pk}")
    public boolean deleteGroup(@PathVariable long pk) {
        if (groupService.findById(pk) == null)
            return false;

        groupService.deleteById(pk);
        return true;
    }

    @DeleteMapping("/students/{pk}")
    public boolean deleteStudent(@PathVariable long pk) {
        if (studentService.findById(pk) == null)
            return false;

        studentService.deleteById(pk);
        return true;
    }

    @PostMapping
    public GroupPojo createGroup(@RequestBody GroupPojo pojo) {
        return groupService.createGroup(pojo);
    }

    @PostMapping("/{groupId}/students")
    public StudentPojo createStudent(@PathVariable long groupId, @RequestBody StudentPojo pojo) {
        return groupService.createStudent(groupId, pojo);
    }

    @PutMapping("/{pk}")
    public GroupPojo updateGroup(@PathVariable long pk, @RequestBody GroupPojo pojo) {
        return groupService.updateGroup(pk, pojo);
    }

    @PutMapping("/{groupId}/students/{studentId}")
    public StudentPojo updateStudent(@PathVariable long groupId, @PathVariable long studentId, @RequestBody StudentPojo pojo) {
        return groupService.updateStudent(groupId, studentId, pojo);
    }
}