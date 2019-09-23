package com.ats.qosmpp.service;

import com.ats.qosmpp.domain.User;
import com.ats.qosmpp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {

        User user =userRepository.findByClientId(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        return user;
    }
}
