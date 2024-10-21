# Project Description



This project is destinate to known how work <b>Spring AOP</b> through Points cut. For example:

## Annotations

```java
@PreAuthorize
@PostAuthorize
```

This annotations are execute before or after the succefull authentication.

<b>Spring AOP</b> can't access at interface annotations using:

```java
@Pointcut("@annotations(...)") 
```

For can get access to annotation interface with <b>@Pointcut</b>, is need access through metadata class with this way 

```java
public static boolean getAnnotation(Class<?> clazz, Class<? extends Annotation> annotation)  {
    Class<?>[] interfacesClasses = clazz.getInterfaces();
    
    boolean isAnnotationPresent = false;

    for (Class<?> interfaceClass: interfacesClasses) {
        Method[] methodsFromInterface = interfaceClass.getMethods();

        for (Method methodFromInterface: methodsFromInterface) {
            isAnnotationPresent = methodFromInterface.isAnnotationPresent(annotation);
        }
    }

    return isAnnotationPresent;
}
```

Then, we can access to interface annotations from <b>@Pointcut</b>


## Call aspect

In this scenary, is verify that <b>@RoleAdmin</b> and <b>@RoleUser</b> annotations is are present before that authentication method is executed. 

```java
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
```

## Annotation in IApiController Interface

```java
public interface IApiController {
    @PostMapping("/private-key")
    @RoleAdmin
    ResponseEntity<ResponseEntityDTO<String>> getPrivateKey();
}
```

## Technologies used

* Spring Boot
* Spring Security
* Spring Data
* Spring AOP