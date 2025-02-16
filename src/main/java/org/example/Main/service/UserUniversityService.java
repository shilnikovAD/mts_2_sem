package org.example.Main.service;

import org.example.Main.model.UserUniversity;
import org.example.Main.repository.UserUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserUniversityService {

  private static final Logger logger = LoggerFactory.getLogger(UserUniversityService.class);

  @Autowired
  private UserUniversityRepository userUniversityRepository;

  public List<UserUniversity> getAllUniversities() {
    logger.info("Fetching all universities");
    return userUniversityRepository.findAll();
  }

  public UserUniversity createUniversity(UserUniversity userUniversity) {
    logger.info("Creating new university: {}", userUniversity.getUniversityName());
    return userUniversityRepository.save(userUniversity);
  }
}
