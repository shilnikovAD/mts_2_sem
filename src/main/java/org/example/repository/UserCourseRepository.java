package org.example.repository;

import java.util.List;
import org.example.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
  List<UserCourse> findByUserId(Long userId);
}
