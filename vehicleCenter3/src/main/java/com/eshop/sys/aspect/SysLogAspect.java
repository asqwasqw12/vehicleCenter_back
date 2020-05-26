package com.eshop.sys.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.eshop.common.HttpUtils;
import com.eshop.common.IPUtils;
import com.eshop.common.SecurityUtils;
import com.eshop.common.StringUtils;
import com.eshop.sys.pojo.SysLog;
import com.eshop.sys.service.SysLogService;


@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("execution(* com.eshop.*.service.*.*(..))")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		String userName = SecurityUtils.getUsername();
		if(joinPoint.getTarget() instanceof SysLogService) {
			return ;
		}
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		SysLog sysLog = new SysLog();		

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
       

		// 获取request
		HttpServletRequest request = HttpUtils.getHttpServletRequest();
		// 设置IP地址
		//sysLog.setIp(IPUtils.getIpAddr(request));
		
		//设置IP地址
		String ip = StringUtils.getIp(request);
		sysLog.setIp(ip);
		
		//设置所在区域
		sysLog.setAddress(StringUtils.getCityInfo(ip));
		
		//设置浏览器
		sysLog.setBrowser(StringUtils.getBrowser(request));

		// 用户名
		sysLog.setUserName(userName);
		
		// 执行时长(毫秒)
		sysLog.setTime(time);
		
		// 保存系统日志
		sysLogService.save(sysLog);
	}

}
