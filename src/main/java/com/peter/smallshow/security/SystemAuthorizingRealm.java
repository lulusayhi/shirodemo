package com.peter.smallshow.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.peter.smallshow.entity.Roles;
import com.peter.smallshow.entity.Users;
import com.peter.smallshow.service.UserService;

@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	@Resource(name = "userService")
	private UserService userService;

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		System.out.println("开始---------------------------");
//        String userName = (String) principals.fromRealm(getName()).iterator().next(); 
        String userName = (String)super.getAvailablePrincipal(principals);
        System.out.println(userName);
        //根据用户名查找拥有的角色  
        List<Roles> roles = userService.getUserRoles(userName); 
        
        if (roles != null) {
//        	for(Roles r : roles){
//            	System.out.println(r.toString());
//            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
  
            for (Roles role : roles) {  
                info.addRole(role.getName());  
            }  
  
            return info;  
        } else {  
            return null;  
        }  
    } 

	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Users user = userService.getUser(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getName(), user.getPasswd(), "");
		} else {
			return null;
		}
	}
}
