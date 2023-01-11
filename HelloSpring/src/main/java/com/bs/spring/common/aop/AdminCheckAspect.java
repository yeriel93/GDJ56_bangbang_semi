package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;

@Component
@Aspect
public class AdminCheckAspect {
	
	@Before("execution(* com.bs.spring.memo..select*(..))")
	public void adminCheck(JoinPoint jp) {
		
		//�α������� ��������
		//RequestContextHolderŬ�������� currentRequestAttribute() static�޼ҵ� ����
		//currentRequestAttribute() �޼ҵ带 �̿��� session��ü�� ������ �� �ִ�. 
		HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		//AOP�� ������ ���� ������ ������ �ߴ��ϴ� ����� ������ ������ ��� �ۿ��� ���� 
		if(loginMember==null || !loginMember.getUserId().equals("admin")) {
			throw new AdminAccessException("�����ڸ� ������ �� �ֽ��ϴ�! :( ");
		}
	}
}
