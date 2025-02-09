package org.example.Main.service;

import org.example.Main.model.UserCourse;
import org.example.Main.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserCourseService {

  private static final Logger logger = LoggerFactory.getLogger(UserCourseService.class);

  @Autowired
  private UserCourseRepository userCourseRepository;

  public List<UserCourse> getAllCourses() {
    logger.info("Fetching all courses");
    return userCourseRepository.findAll();
  }

  public UserCourse createCourse(UserCourse userCourse) {
    logger.info("Creating new course: {}", userCourse.getCourseName());
    return userCourseRepository.save(userCourse);
  }
}
