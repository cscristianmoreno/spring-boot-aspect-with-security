package com.security.spring_app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.security.spring_app.aop.pointcut.PointcutExecutions;
import com.security.spring_app.entity.Auditory;
import com.security.spring_app.enums.AuditoryTypes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Component
@Aspect
public class RepositoryInterceptor extends PointcutExecutions {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @AfterReturning("allMethodsInRepository()")
    public void executesuccessQuery(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String type = AuditoryTypes.SUCCESS.toString();

        updateAuditory(name, type, "");
    }
    
    @AfterThrowing(pointcut = "executeAllMethodsInRepository()", throwing = "exception")
    public void executeexceptionQuery(JoinPoint joinPoint, Exception exception) throws Throwable {
        String name = joinPoint.getSignature().getName();
        String type = AuditoryTypes.EXCEPTION.toString();

        updateAuditory(name, type, exception.getMessage());
    }

    public void updateAuditory(final String name, final String type, final String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Auditory auditory = new Auditory();
        auditory.setName(name);
        auditory.setType(type);

        if (description.length() > 0) {
            auditory.setDescription(description);
        }
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(auditory);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }
    }
}
