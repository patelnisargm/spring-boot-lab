package com.nmp.restapi.service;

import com.nmp.restapi.entity.User;

public interface IUserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
