package com.security.spring_app.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.security.spring_app.aop.pointcut.PointcutExecutions;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuthControllerInterceptor extends PointcutExecutions {
    
    @AfterReturning(pointcut = "loginMethod()")
    public void loginInterceptor() {
    }
}
