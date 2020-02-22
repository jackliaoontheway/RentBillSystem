package com.cherryj.base.common.shiro;

import com.cherryj.base.domain.UserAccount;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class UserAccountCredentialMatcher implements CredentialsMatcher
{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
    {
        UserAccount user = (UserAccount) token.getPrincipal();
        UserAccount userAcc = (UserAccount) info.getPrincipals().getPrimaryPrincipal();
        if (userAcc == null)
        {
            return false;
        }
        //TODO
        //boolean res = userAcc.isSameUser(user.getUsername(), user.getStringPassword());
        return true;
    }
}

