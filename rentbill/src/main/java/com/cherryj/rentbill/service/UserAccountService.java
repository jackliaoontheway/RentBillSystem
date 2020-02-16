package com.cherryj.rentbill.service;

import com.cherryj.rentbill.common.model.Response;
import com.cherryj.rentbill.domain.UserAccount;

public interface UserAccountService {

    Response<UserAccount> register(UserAccount userAccount);

    Response<UserAccount> login(UserAccount userAccount);

    UserAccount findById(Integer userAccountId);
}
