package com.security.spring_app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.security.spring_app.annotations.RoleAdmin;
import com.security.spring_app.annotations.RoleUser;
import com.security.spring_app.aop.pointcut.PointcutExecutions;
import com.security.spring_app.enums.RolesTypes;
import com.security.spring_app.utils.IsRoleAnnotationPresentIninterface;

@Component
@Aspect
@EnableAspectJAutoProxy
public class RoleSecurityInterceptor extends PointcutExecutions {
    
    @Before("getMethodsAnnotedWithRoleSecurity()")
    public void roleAdminInterceptor(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
        Class<?> clazz = joinPoint.getTarget().getClass(); 

        if (!IsRoleAnnotationPresentIninterface.getAnnotation(clazz, RoleAdmin.class)) {
            return;
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        IsRoleAnnotationPresentIninterface.verifyAuthentication(authentication, RolesTypes.ADMIN);
    }

    @Before("getMethodsAnnotedWithRoleSecurity()")
    public void roleUserInterceptor(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
        Class<?> clazz = joinPoint.getTarget().getClass(); 

        if (!IsRoleAnnotationPresentIninterface.getAnnotation(clazz, RoleUser.class)) {
            return;
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        IsRoleAnnotationPresentIninterface.verifyAuthentication(authentication, RolesTypes.USER);
    }
}
