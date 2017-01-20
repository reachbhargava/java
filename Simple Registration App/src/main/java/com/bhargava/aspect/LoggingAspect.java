package com.bhargava.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Aspect file that defines pointcuts and before, after advices at points of interest.
 * 
 * @author Bhargava
 *
 */
@Aspect
public class LoggingAspect {

	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(public * com.bhargava.controller.*.*(..))")
	public void controllerBefore(JoinPoint joinPoint) {
		logger.info("Before calling CONTROLLER method " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(public * com.bhargava.controller.*.*(..))", returning = "result")
	public void controllerAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Returning from CONTROLLER method " + joinPoint.getSignature().getName()
				+ "Method returned value is : " + result);
	}

	@Before("execution(public * com.bhargava.service.*.*(..))")
	public void serviceBefore(JoinPoint joinPoint) {
		logger.info("Before calling SERVICE method " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(public * com.bhargava.service.*.*(..))", returning = "result")
	public void serviceAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Returning from SERVICE method " + joinPoint.getSignature().getName()
				+ "Method returned value is : " + result);
	}

	@AfterThrowing(pointcut = "execution(public * com.bhargava.service.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		logger.error("Exception at " + joinPoint.getSignature().getName());
		logger.error("Exception " + error);
//		error.printStackTrace();
	}

	@Before("execution(public * com.bhargava.dao.*.*(..))")
	public void daoBefore(JoinPoint joinPoint) {
		logger.info("Before calling DAO method " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(public * com.bhargava.dao.*.*(..))", returning = "result")
	public void daoAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Returning from DAO method " + joinPoint.getSignature().getName() + "Method returned value is : "
				+ result);
	}

}
