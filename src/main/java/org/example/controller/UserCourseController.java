package org.example.controller;

import org.example.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-courses")
public class UserCourseController {

  @Autowired
  private UserCourseService userCourseService;

  @GetMapping
  public String getAllCourses() {
    return "testusercourse";
  }

}
