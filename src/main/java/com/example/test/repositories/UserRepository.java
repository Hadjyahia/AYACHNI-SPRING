package com.example.test.repositories;


import com.example.test.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
  //AppUser findByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE AppUser a " +
          "SET a.enabled = TRUE WHERE a.email = ?1")
  int enableAppUser(String email);

  Optional<AppUser> findByEmail(String email);
}
