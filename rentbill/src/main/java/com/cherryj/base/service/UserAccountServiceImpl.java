package com.cherryj.base.service;

import com.cherryj.base.common.model.Response;
import com.cherryj.base.common.model.ResponseStatus;
import com.cherryj.base.common.shiro.CryptoUtil;
import com.cherryj.base.domain.UserAccount;
import com.cherryj.base.domain.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public Response<UserAccount> register(UserAccount userAccount) {

        Response<UserAccount> response = new Response<>();

        UserAccount existedUserAccount = userAccountRepository.findByUserName(userAccount.getUserName());
        if (existedUserAccount != null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("This username is already existed.");
            return response;
        }

        UserAccount registerUserAccount = new UserAccount();
        registerUserAccount.setUserName(userAccount.getUserName());

        CryptoUtil.encryptPassword(userAccount);

        registerUserAccount = userAccountRepository.save(registerUserAccount);
        response.setData(registerUserAccount);
        return response;
    }

    @Override
    public Response<UserAccount> login(UserAccount userAccount) {
        Response<UserAccount> response = new Response<>();

        UserAccount existedUserAccount = userAccountRepository.findByUserName(userAccount.getUserName());
        if (existedUserAccount == null) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Username or password is incorrect.");
            return response;
        }

        if (!CryptoUtil.validatePassword(existedUserAccount.getPasswordHash(), userAccount.getPassword(), existedUserAccount.getPasswordSalt())) {
            response.setStatus(ResponseStatus.RequestParameterError.name());
            response.setMsg("Username or password is incorrect.");
            return response;
        }
        response.setData(existedUserAccount);
        return response;
    }

    @Override
    public UserAccount findById(Integer userAccountId) {
        return userAccountRepository.getOne(userAccountId);
    }

    @Override
    public UserAccount findByUserName(String userName) {
        return userAccountRepository.findByUserName(userName);
    }
}
