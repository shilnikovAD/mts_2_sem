package org.example.Main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserUniversity {

  @Id
  private Long id;
  private String universityName;

  public String getUniversityName() {
    return universityName;
  }

  public void setUniversityName(String universityName) {
    this.universityName = universityName;
  }

}
