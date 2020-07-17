package com.ttt.shiro;

import com.ttt.pojo.Studen;
import com.ttt.service.StudentService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo ();
        info.addStringPermission("user:add");
        return info;
    }
    @Autowired
    private StudentService studentService;
    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //判断密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Studen studen = studentService.getStudentByName(token.getUsername());
        System.out.println(studen);
        if (studen == null) {
            //shiro底层会抛出UnKnowAccountException
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo("",studen.getSpas(),"");
    }
}
