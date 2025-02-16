package org.example.Main.repository;

import org.example.Main.model.UserUniversity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUniversityRepository extends JpaRepository<UserUniversity, Long> {
}
