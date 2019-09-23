package com.ats.qosmpp.service;

import com.ats.qosmpp.domain.User;

public interface UserService {
    User findByUserName(String userName);
}
