package com.cherryj.base.common.shiro;

import com.cherryj.base.domain.Functionality;
import com.cherryj.base.domain.UserAccount;
import com.cherryj.base.domain.UserRole;
import com.cherryj.base.service.UserAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CherryJShiroRealm extends AuthorizingRealm {

//    @Autowired
//    private UserAccountService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserAccount userAcc = (UserAccount) SecurityUtils.getSubject().getPrincipal();
        UserAccount user = (UserAccount) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (UserRole role : user.getRoles()) {
            authorizationInfo.addRole(role.getCode());
            List<Functionality> functionalities = role.getFunctionalities();
            if (functionalities != null && functionalities.size() > 0) {
                for (Functionality functionality : role.getFunctionalities()) {
                    authorizationInfo.addStringPermission(functionality.getCode());
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserAccount user = (UserAccount) token.getPrincipal();
        if (user == null) {
            throw new AccountException("Username or password incorrect.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getUserName(), getName());
        return authenticationInfo;
    }

}
