package com.wts.service;

import com.wts.common.model.User;

public class UserService {
    private User dao = new User().dao();

    public User goLogin(String login, String pass) {
        return dao.findFirst("SELECT * FROM user WHERE login=? AND password=? AND state = 1", login, pass);
    }
}
