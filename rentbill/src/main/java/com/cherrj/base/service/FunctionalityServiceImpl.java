package com.cherrj.base.service;

import com.cherrj.base.common.model.Response;
import com.cherrj.base.common.model.ResponseStatus;
import com.cherrj.base.common.utils.CryptoUtil;
import com.cherrj.base.domain.Functionality;
import com.cherrj.base.domain.FunctionalityRepository;
import com.cherrj.base.domain.UserAccount;
import com.cherrj.base.domain.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionalityServiceImpl implements FunctionalityService {

    @Autowired
    private FunctionalityRepository functionalityRepository;

}
