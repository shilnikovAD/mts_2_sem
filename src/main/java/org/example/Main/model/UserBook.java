package org.example.Main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserBook {

  @Id
  private Long id;
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
