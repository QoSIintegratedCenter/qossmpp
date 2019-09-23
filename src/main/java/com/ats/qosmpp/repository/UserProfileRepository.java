package com.ats.qosmpp.repository;

import com.ats.qosmpp.domain.Users_Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<Users_Profile, Long> {
    Users_Profile findByUsername(String username);
}
