package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

//Aspect�� ����Ͽ� ����޼ҵ带 Ư�������� �����Ű��!!!
@Slf4j
public class LoggerAspect {
	
	//aspectŬ�������� �޼ҵ带 �����Ҷ��� ���� advisor�� ���� �޼ҵ� �������� �޶���
	//Ÿ�ٸ޼ҵ尡 ����Ǳ� ���� �����ϴ� �޼ҵ� �����ϱ� -> Before
	public void loggerBefore(JoinPoint jp) { //���� ������ �޼ҵ� ����
		log.debug("loggerAspect ������");
		
		//JoinPoint��ü aop������ ���� �޼ҵ尡 ����ɶ� ������ Ȯ���� �� ����
		//Ÿ��Ŭ������ �޼ҵ� Ȯ���ϱ�
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" : "+sig.getName());
		log.debug("==============================");
		
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("loggerAfter�޼ҵ� ����");
	}
}
