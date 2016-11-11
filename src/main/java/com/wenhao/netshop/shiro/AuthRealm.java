package com.wenhao.netshop.shiro;

import com.wenhao.netshop.dao.UserMapper;
import com.wenhao.netshop.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by lw on 2016/11/7.
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private UserMapper dao;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> role = dao.findRole(username);
        Set<String> permission = dao.findPermission(username);
        info.setRoles(role);
        info.setStringPermissions(permission);
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        User user = dao.selectByUsername(username);
        if (user != null) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "a");
            return info;
        } else {
            return null;
        }
    }
}
