package ru.dungeon.aimasters.backend.aspects;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Aspect
@Slf4j
@Component
public class ControllerLoggingAspect {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void controller() {
  }

  @Before("controller()")
  public void logBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    log.info(
        "Входящий запрос\n {} {} with body: {}",
        request.getMethod(),
        request.getRequestURI(),
        request.getQueryString());
  }

  @AfterReturning(pointcut = "controller()", returning = "result")
  public void logAfterReturning(Object result) {
    log.info("Ответ на запрос\n {}", result);
  }
}
