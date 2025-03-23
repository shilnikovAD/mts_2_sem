package org.example.service;

import org.example.model.UserCourse;
import org.example.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserCourseService {

  private static final Logger logger = LoggerFactory.getLogger(UserCourseService.class);

  @Autowired
  private UserCourseRepository userCourseRepository;

  private boolean courseCreated = false;

  /**
   * Гарантия: метод будет выполнен **ровно один раз**, даже если несколько запросов
   * попытаются создать курс одновременно. Для этого используется флаг `courseCreated`,
   * который блокирует повторный вызов метода, пока первый не завершится.
   */
  public synchronized UserCourse createCourseExactlyOnce(UserCourse userCourse) {
    if (courseCreated) {
      throw new IllegalStateException("Course has already been created.");
    }

    logger.info("Creating new course: {}", userCourse.getCourseName());
    UserCourse createdCourse = userCourseRepository.save(userCourse);
    courseCreated = true;

    return createdCourse;
  }
}
