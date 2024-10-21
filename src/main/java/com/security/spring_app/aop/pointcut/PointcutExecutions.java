package com.security.spring_app.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExecutions {
    
    @Pointcut("execution(* com..CrudRepository.*(..))")
    public void allMethodsInRepository() {}

    @Pointcut("execution(public String login(..))")
    public void loginMethod() {}

    @Pointcut("execution(* com..ApiController.*(..))") 
    public void getMethodsAnnotedWithRoleSecurity() {}
}
