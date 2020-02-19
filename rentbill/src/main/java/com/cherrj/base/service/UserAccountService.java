package com.cherrj.base.service;

import com.cherrj.base.common.model.Response;
import com.cherrj.base.domain.UserAccount;

public interface UserAccountService {

    Response<UserAccount> register(UserAccount userAccount);

    Response<UserAccount> login(UserAccount userAccount);

    UserAccount findById(Integer userAccountId);
}
