package org.example.controller;

import org.example.service.UserUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-universities")
public class UserUniversityController {

  @Autowired
  private UserUniversityService userUniversityService;

  @GetMapping
  public String getAllUniversities() {
    return "testuserUneversity";
  }
}
