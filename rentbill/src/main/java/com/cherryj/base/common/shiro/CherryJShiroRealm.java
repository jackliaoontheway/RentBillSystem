package com.cherryj.base.common.shiro;

import com.cherryj.base.domain.Functionality;
import com.cherryj.base.domain.UserAccount;
import com.cherryj.base.domain.UserRole;
import com.cherryj.base.service.UserAccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CherryJShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserAccountService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();

        UserAccount user = userService.findByUserName(username);

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
        String username = (String) token.getPrincipal();
        UserAccount user = userService.findByUserName(username);
        if (user == null) {
            throw new AccountException("Username or password incorrect.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()), getName());
        return authenticationInfo;
    }

}
