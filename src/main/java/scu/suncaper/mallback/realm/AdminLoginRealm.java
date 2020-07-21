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

import java.util.Set;

public class AdminLoginRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
//    @Autowired
//    private AdminPermissionService adminPermissionService;
//    @Autowired
//    private AdminRoleService adminRoleService;

//TODO
    // 重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户的所有权限
//        String username = principalCollection.getPrimaryPrincipal().toString();
//        Set<String> permissions = adminPermissionService.listPermissionURLsByUser(username);

        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
//        s.setStringPermissions(permissions);
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
