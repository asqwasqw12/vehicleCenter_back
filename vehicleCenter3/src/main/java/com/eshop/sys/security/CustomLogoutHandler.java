package com.eshop.sys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.eshop.aop.Log;

@Component
public class CustomLogoutHandler implements LogoutHandler {
	
	@Log("退出登录")
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("执行了退出登录");
        /*//确定注入了tokenStore
        Assert.notNull(tokenStore, "tokenStore must be set");
       //获取头部的认证信息
        String token = request.getHeader("Authorization");
        Assert.hasText(token, "token must be set");
        //校验token是否符合JwtBearer格式
        if (isJwtBearerToken(token)) {
            token = token.substring(6);
            OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
            OAuth2RefreshToken refreshToken;
            if (existingAccessToken != null) {
                if (existingAccessToken.getRefreshToken() != null) {
                    LOGGER.info("remove refreshToken!", existingAccessToken.getRefreshToken());
                    refreshToken = existingAccessToken.getRefreshToken();
                    tokenStore.removeRefreshToken(refreshToken);
                }
                LOGGER.info("remove existingAccessToken!", existingAccessToken);
                tokenStore.removeAccessToken(existingAccessToken);
            }
            return;
        } else {
            throw new BadClientCredentialsException();
        }

    }
*/
	
  }
}
