package org.example.Main.service;

import org.example.Main.model.UserCourse;
import org.example.Main.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {

  @Autowired
  private UserCourseRepository userCourseRepository;

  public List<UserCourse> getAllCourses() {
    return userCourseRepository.findAll();
  }

  public UserCourse createCourse(UserCourse userCourse) {
    return userCourseRepository.save(userCourse);
  }
}
