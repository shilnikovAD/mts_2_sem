package org.example.Main.controller;

import org.example.Main.service.UserUniversityService;
import org.example.Main.model.UserUniversity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
