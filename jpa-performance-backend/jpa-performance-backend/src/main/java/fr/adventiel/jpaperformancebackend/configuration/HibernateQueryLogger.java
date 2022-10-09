package fr.adventiel.jpaperformancebackend.configuration;

import com.yannbriancon.interceptor.HibernateQueryCountInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class HibernateQueryLogger {

    private final HibernateQueryCountInterceptor hibernateQueryCountInterceptor;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logQueryCount(ProceedingJoinPoint joinPoint) throws Throwable {
        hibernateQueryCountInterceptor.startCounter();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            log.info("Number of queries executed: {}", hibernateQueryCountInterceptor.getQueryCount());
        }
    }
}
