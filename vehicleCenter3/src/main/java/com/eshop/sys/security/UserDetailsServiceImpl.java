package com.eshop.sys.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysUserService;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	 @Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("执行UserDetails loadUserByUsername...");
		SysUser user = sysUserService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = sysUserService.findPermissions(user.getName());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        for(GrantedAuthority authority:grantedAuthorities) {
        System.out.println("Authority:"+authority.getAuthority());
        }
        //return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
        return createJwtUserDetails(user,grantedAuthorities);
	}
	
	private UserDetails createJwtUserDetails(SysUser user,List<GrantedAuthority> grantedAuthorities) {
        return new JwtUserDetails(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getSalt(),
                user.getRealName(),
                user.getNickName(),               
                user.getAvatar(),
                user.getEmail(),
                user.getMobile(),
                user.getDeptId(),
                user.getDeptName(),
                user.getJob(),
                user.getRoleNames(),
                user.getCreateTime(),
                grantedAuthorities        
        );
    }

}
