package com.ssau.study.controller;

import com.ssau.study.annotations.IsAdmin;
import com.ssau.study.dto.GroupPojo;
import com.ssau.study.dto.StudentPojo;
import com.ssau.study.service.GroupService;
import com.ssau.study.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/id/{pk}")
    public GroupPojo findById(@PathVariable long pk) {
        return groupService.findById(pk);
    }

    @GetMapping("/{name}")
    public List<GroupPojo> findAllByName(@PathVariable String name) {
        return groupService.findAllByName(name);
    }

    @GetMapping("/{groupId}/students")
    @IsAdmin
    public List<StudentPojo> findAllStudents(@PathVariable long groupId) {
        return groupService.findStudentsByGroup(groupId);
    }

    @DeleteMapping("/{pk}")
    @IsAdmin
    public boolean deleteGroup(@PathVariable long pk) {
        if (groupService.findById(pk) == null)
            return false;

        groupService.deleteById(pk);
        return true;
    }

    @DeleteMapping("/students/{pk}")
    @IsAdmin
    public boolean deleteStudent(@PathVariable long pk) {
        if (studentService.findById(pk) == null)
            return false;

        studentService.deleteById(pk);
        return true;
    }

    @PostMapping
    @IsAdmin
    public ResponseEntity<GroupPojo> createGroup(@RequestBody GroupPojo pojo) {
        try {
            return new ResponseEntity<>(groupService.createGroup(pojo),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{groupId}/students")
    @IsAdmin
    public ResponseEntity<StudentPojo> createStudent(@PathVariable long groupId, @RequestBody StudentPojo pojo) {
        try {
            return new ResponseEntity<>(groupService.createStudent(groupId, pojo), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{pk}")
    @IsAdmin
    public ResponseEntity<GroupPojo> updateGroup(@PathVariable long pk, @RequestBody GroupPojo pojo) {
        try {
            return new ResponseEntity<>(groupService.updateGroup(pk, pojo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{groupId}/students/{studentId}")
    @IsAdmin
    public ResponseEntity<StudentPojo> updateStudent(@PathVariable long groupId, @PathVariable long studentId, @RequestBody StudentPojo pojo) {
        try {
            return new ResponseEntity<>(groupService.updateStudent(groupId, studentId, pojo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
