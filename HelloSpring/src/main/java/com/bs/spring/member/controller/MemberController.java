package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

@Controller
// �⺻���� �ּҸ� ���� ���� 
@RequestMapping("/member")
public class MemberController {
	//�����ϴ� ��� Ŭ������ ���������
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
	}
	
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("controller - test() ����");
//		service.test();
//	}
	
	@RequestMapping("/loginMember.do")
//	public String loginMember(String userId, String password) {
//	public String loginMember(@RequestParam Map m) {
	public String loginMember(Member m, HttpSession session) {
		//Session�� �����͸� �����ϰ� ����
		
		Member loginMember = service.selectMemberById(m);
//		System.out.println(loginMember);
		
		//��ȣȭ�� �н����带 �������̶� ���ϱ� ���ؼ���
		//BCryptPasswordEncoderŬ������ �����ϴ� �޼ҵ带 �̿��ؼ� �����񱳸� �ؾ��Ѵ�.
		//matches("������", ��ȣȭ��)�޼ҵ带 �̿�
		if(loginMember!= null && 
				//loginMember.getPassword().equals(m.getPassword())) { 
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			//�α��� ����
			session.setAttribute("loginMember", loginMember);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logoutMember.do")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		//�н����� ��ȣȭ ó���ϱ�
		String encodePassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
		int result = service.insertMember(m);
//		System.out.println(result);
		
		if(result>0) {
			mv.addObject("msg","ȸ������ �Ϸ�");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","ȸ������ ����");
			mv.addObject("loc","/member/enrollMember.do");
		}
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/memberView.do")
	public String enrollLsit() {
		return "member/memberView";
	}
}
