package com.cherryj.base.service;

import com.cherryj.base.common.model.Response;
import com.cherryj.base.domain.UserAccount;

public interface UserAccountService {

    Response<UserAccount> register(UserAccount userAccount);

    Response<Boolean> login(UserAccount userAccount);

    UserAccount findById(Integer userAccountId);

    UserAccount findByUserName(String userName);
}
