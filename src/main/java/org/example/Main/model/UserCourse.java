package org.example.Main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserCourse {

  @Id
  private Long id;
  private String courseName;


}
