package org.example.Main.service;

import org.example.Main.model.UserUniversity;
import org.example.Main.repository.UserUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUniversityService {

  @Autowired
  private UserUniversityRepository userUniversityRepository;

  public List<UserUniversity> getAllUniversities() {
    return userUniversityRepository.findAll();
  }

  public UserUniversity createUniversity(UserUniversity userUniversity) {
    return userUniversityRepository.save(userUniversity);
  }
}
