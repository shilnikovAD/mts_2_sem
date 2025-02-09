package org.example.Main.controller;

import org.example.Main.service.UserCourseService;
import org.example.Main.model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-courses")
public class UserCourseController {

  @Autowired
  private UserCourseService userCourseService;

  @GetMapping
  public List<UserCourse> getAllCourses() {
    return userCourseService.getAllCourses();
  }

  @PostMapping
  public UserCourse createCourse(@RequestBody UserCourse userCourse) {
    return userCourseService.createCourse(userCourse);
  }
}
