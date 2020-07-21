package scu.suncaper.mallback.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import scu.suncaper.mallback.pojo.Admin;
import scu.suncaper.mallback.service.AdminService;

public class AdminLoginRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;

    // 重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码和盐并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String adminName = token.getPrincipal().toString();
        Admin admin = adminService.findByAname(adminName);
        if (ObjectUtils.isEmpty(admin)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = admin.getPassword();
        String salt = admin.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, passwordInDB, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }
}
