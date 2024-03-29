package com.eshop.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.eshop.sys.security.GrantedAuthorityImpl;
import com.eshop.sys.security.JwtAuthenticatioToken;
import com.eshop.sys.security.JwtUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名称
	 */
	private static final String USERNAME = Claims.SUBJECT;
	/**
	 * 创建时间
	 */
	private static final String CREATED = "created";
	/**
	 * 权限列表
	 */
	private static final String AUTHORITIES = "authorities";
	/**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";
    /**
     * 有效期12小时
     */
   // private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    
    
    
    /**
	 * 生成令牌
	 *
	 * @param userDetails 用户
	 * @return 令牌
	 */
	public static String generateToken(Authentication authentication) {
		//System.out.println("开始生成claim...");
		log.info("开始生成claim...");
	    Map<String, Object> claims = new HashMap<>(3);
	    claims.put(USERNAME, SecurityUtils.getUsername(authentication));
	    claims.put(CREATED, new Date());
	    claims.put(AUTHORITIES, authentication.getAuthorities());
	    return generateToken(claims);
	}

	/**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
    	//System.out.println("开始生成token...");
    	System.out.println("开始生成token...");
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
	 * 从令牌中获取用户名
	 *
	 * @param token 令牌
	 * @return 用户名
	 */
	public static String getUsernameFromToken(String token) {
	    String username;
	    try {
	        Claims claims = getClaimsFromToken(token);
	        username = claims.getSubject();
	    } catch (Exception e) {
	    	//System.out.println("解析token错误");
	    	log.info("解析token错误");
	        username = null;
	    }
	    return username;
	}
	
	/**
	 * 根据请求令牌获取登录认证信息
	 * @param token 令牌
	 * @return 用户名
	 */
	public static Authentication getAuthenticationeFromToken(HttpServletRequest request) {
		Authentication authentication = null;
		// 获取请求携带的令牌
		String token = JwtTokenUtils.getToken(request);
		
		// 请求令牌不能为空
		if(token != null) {
			// 上下文中Authentication为空
			if(SecurityUtils.getAuthentication() == null) {		
				//System.out.println("获取服务端的Authenticaiton为null");
				log.info("获取服务端的Authenticaiton为null");
				Claims claims = getClaimsFromToken(token);
				if(claims == null) {
					System.out.println("token解析错误，设置服务端的Authenticaiton不成功，null返回");
					return null;
				}
				String username = claims.getSubject();
				if(username == null) {
					//System.out.println("token解析错误，设置服务端的Authenticaiton不成功，null返回");
					return null;
				}
				if(isTokenExpired(token)) {
					//System.out.println("token过期，设置服务端的Authenticaiton不成功，null返回");
					return null;
				}
				Object authors = claims.get(AUTHORITIES);
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				if (authors != null && authors instanceof List) {
					for (Object object : (List) authors) {
						authorities.add(new GrantedAuthorityImpl((String) ((Map) object).get("authority")));
					}
				}
				//System.out.println("生成authentication成功 ");
				User principal = new User(username, "", authorities);
				authentication = new JwtAuthenticatioToken(principal, null, authorities, token);
				
			} else {
				//System.out.println("上下文authentication非空，username="+SecurityUtils.getUsername());
				//System.out.println("解析token，username="+getUsernameFromToken(token));			
				if(validateToken(token, SecurityUtils.getUsername())) {
					// 如果上下文中Authentication非空，且请求令牌合法，直接返回当前登录认证信息
					//System.out.println("token解析的username和服务端authentication的username相等");
					authentication = SecurityUtils.getAuthentication();
				}else {
					//System.out.println("token解析错误，或者token解析的username和服务端authentication的username不相等!");
					log.info("token解析错误，或者token解析的username和服务端authentication的username不相等!");
				}
			}
		}
		return authentication;
	}

	/**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
	 * 验证令牌
	 * @param token
	 * @param username
	 * @return
	 */
	public static Boolean validateToken(String token, String username) {
	    String userName = getUsernameFromToken(token);
	    //如果解析令牌无误
	    if(userName !=null) {
	       return userName.equals(username);
	    }else {
	    	return false;
	    }
	}

	/**
	 * 刷新令牌
	 * @param token
	 * @return
	 */
	public static String refreshToken(String token) {
	    String refreshedToken;
	    try {
	        Claims claims = getClaimsFromToken(token);
	        claims.put(CREATED, new Date());
	        refreshedToken = generateToken(claims);
	    } catch (Exception e) {
	        refreshedToken = null;
	    }
	    return refreshedToken;
	}

	/**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
    	String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if(token == null) {
        	token = request.getHeader("token");
        } else if(token.contains(tokenHead)){
        	token = token.substring(tokenHead.length());
        } 
        if("".equals(token)) {
        	token = null;
        }
        return token;
    }

}
