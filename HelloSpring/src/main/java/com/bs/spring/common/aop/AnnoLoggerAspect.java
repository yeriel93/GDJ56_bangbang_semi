package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

//������̼����� aop�����ϱ�
@Component
@Aspect
@Slf4j
public class AnnoLoggerAspect {

	//pointcut����ϱ�
	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {}
	
	//advisor���
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		Signature sig = jp.getSignature();
		log.debug(sig.getName()+" ������");
		log.debug(sig.getDeclaringTypeName());
		
		log.debug("�Ķ���� ������ �޾ƿ���");
		Object[] params = jp.getArgs();
		if(params!=null ) {
			for(Object o : params) {
				log.debug("�Ķ���� : {}",o);
			}
		}
		log.debug("==============================");
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig = jp.getSignature();
		log.debug(sig.getName()+" ������");
		log.debug(sig.getDeclaringTypeName());
		log.debug("==============================");
	}
	
	
	
	//���� ���� ��� �����ϴ� �޼ҵ� �����ϱ� 
	
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")
	public Object demoLoggerArount(ProceedingJoinPoint join) throws Throwable{
		
		//������, �ĸ� �����ϴ� �޼ҵ� -> join.proceed();
		StopWatch stop = new StopWatch();
		stop.start();
		//�Ķ���Ͱ��̳� Signature���� ������ �� ����
		Object[] params = join.getArgs();
		Signature sig = join.getSignature();
		
		Object obj = join.proceed();  //������ �ǹǷ� ���� �տ��� ������, �ڿ��� ���� �İ� ��! Object obj = join.proceed();��ġ�� ���� ���İ� ������
		
		stop.stop();
		log.debug("����ð� : "+stop.getTotalTimeMillis()+"ms");
		
		return obj;
	}
	
	
	
	@AfterThrowing("execution(* com.bs.spring..*(..))")
	public void exceptionTest(JoinPoint jp) {
		log.debug("�����߻�!!!!");
	}
}
