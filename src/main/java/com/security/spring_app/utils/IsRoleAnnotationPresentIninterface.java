package com.security.spring_app.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.security.spring_app.enums.RolesTypes;

public abstract class IsRoleAnnotationPresentIninterface {
    
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

    public static void verifyAuthentication(final Authentication authentication, final RolesTypes roleType) {
        Optional<? extends GrantedAuthority> roleAdmin = authentication.getAuthorities().stream()
        .filter((role) -> role.getAuthority().contains(roleType.name())).findFirst();

        if (roleAdmin.isEmpty()) {
            throw new AccessDeniedException("You don't have permission to access this resource.");
        }
    }
}
