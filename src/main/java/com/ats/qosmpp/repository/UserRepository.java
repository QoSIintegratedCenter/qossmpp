package com.ats.qosmpp.repository;

import com.ats.qosmpp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserName(String userName);
    User findByClientId(String clienteId);
}
