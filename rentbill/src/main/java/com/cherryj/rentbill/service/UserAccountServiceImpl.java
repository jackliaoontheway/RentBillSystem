package com.cherryj.rentbill.service;

import com.cherryj.rentbill.common.model.Response;
import com.cherryj.rentbill.common.model.ResponseStatus;
import com.cherryj.rentbill.common.utils.CryptoUtil;
import com.cherryj.rentbill.domain.UserAccount;
import com.cherryj.rentbill.domain.UserAccountRepository;
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
        registerUserAccount.setPasswordSalt(CryptoUtil.generateSalt());
        registerUserAccount.setPasswordHash(CryptoUtil.hashPassword(userAccount.getPassword(), registerUserAccount.getPasswordSalt()));

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
}
