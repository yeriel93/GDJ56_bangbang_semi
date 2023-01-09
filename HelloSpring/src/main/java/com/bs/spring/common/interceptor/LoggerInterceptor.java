package com.bs.spring.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;

//public class LoggerInterceptor implements HandlerInterceptor{
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	
//	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	//��Ȳ�� ���� ������ �޼ҵ带 ������ 
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
		
		log.debug("======= ��ó�� ���ͼ��� ���� =======");
		log.debug("�޼ҵ� ������");
		log.debug(req.getRequestURI());
		Map param = req.getParameterMap();
		for(Object o : param.keySet()) {
			log.debug("{}", o+": "+param.get(o));
		}
		log.debug("================================");
		
		//����޼��� �ۼ��ϱ�
//		res.setContentType("text/html;charset=utf-8");
//		res.getWriter().append("<h2>interceptor�� ������</h2>");
		
		//Object hanlder�̿�
		HandlerMethod method = (HandlerMethod)handler;
		//����Ǵ� Ŭ���� Ȯ���ϱ�
		log.debug("{}", method.getBean());
//		((DemoController)method.getBean()).demo1(req, res);
		
		//����Ǵ� �޼ҵ� Ȯ���ϱ�
		log.debug("{}", method.getMethod());
		
		
		//��ȯ���� true�� controller�޼ҵ带 ����
		//��ȯ���� false�� controller�޼ҵ带 �������� ����
		return true;
	}
	
	//���θ޼ҵ��� ������ �����ڿ� ����Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mv) throws Exception {
		log.debug("======= ��ó�� ���ͼ��� ���� =======");
		log.debug("��û �ּ� {}", req.getRequestURI());
		log.debug("���� ȭ�� : {}", mv.getViewName());
		log.debug("���۵����� : {}", mv.getModel());
		log.debug("===============================");
		
//		throw new IllegalAccessError("�߸��� �����Դϴ�!");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("======= ���� �� ���ͼ��� ���� =======");
		log.debug("��û �ּ� {}", request.getRequestURI());
		log.debug("�����޼��� : {}", ex!=null ? ex.getMessage() : "����");
		log.debug("===============================");
	}
	
	
}
