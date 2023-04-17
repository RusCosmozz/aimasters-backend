package ru.dungeon.aimasters.backend.aspects;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.toJson;

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
  @Around(
          "controller() && " +
                  "execution(* *(.., @org.springframework.web.bind.annotation.RequestBody (*), ..)) && " +
                  "args(body, ..)"
  )
  public Object processWithBody(ProceedingJoinPoint joinPoint, Object body) throws Throwable {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    log.info(
            "\nВходящий запрос\n {} {} \n {}",
            request.getMethod(),
            request.getRequestURI(),
            toJson(body));
    Object proceed = joinPoint.proceed();
    log.info(
            "\nОтвет на запрос\n {} {} \n {}",
            request.getMethod(),
            request.getRequestURI(),
            toJson(proceed));
    return proceed;
  }
  @Around(
          "controller() && (" +
                  "execution(* *(!@org.springframework.web.bind.annotation.RequestBody (*), ..)))"
  )
  public Object processWithoutBody(ProceedingJoinPoint joinPoint) throws Throwable {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    log.info(
            "\nВходящий запрос\n {} {}",
            request.getMethod(),
            request.getRequestURI());
    Object proceed = joinPoint.proceed();
    log.info(
            "\nОтвет на запрос\n {} {} \n {}",
            request.getMethod(),
            request.getRequestURI(),
            toJson(proceed));
    return proceed;
  }
}
