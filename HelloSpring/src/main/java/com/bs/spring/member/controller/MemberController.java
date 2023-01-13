package com.bs.spring.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
// �⺻���� �ּҸ� ���� ���� 
@RequestMapping("/member")
//Model����� ���̵� �߿� ���� Ű���� ������ �ִ� ���̵��� �����ֱ⸦ session ó�� �� �� ����  
@SessionAttributes({"loginMember"})
//lombok���� �����ϴ� �α� 
@Slf4j
public class MemberController {
	//�����ϴ� ��� Ŭ������ ���������
	
//	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
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
	public String loginMember(Member m, Model model) {
		//Session�� �����͸� �����ϰ� ����
		
		Member loginMember = service.selectMemberById(m);
//		System.out.println(loginMember);
		
		//��ȣȭ�� �н����带 �������̶� ���ϱ� ���ؼ���
		//BCryptPasswordEncoderŬ������ �����ϴ� �޼ҵ带 �̿��ؼ� ����񱳸� �ؾ��Ѵ�.
		//matches("������", ��ȣȭ��)�޼ҵ带 �̿�
		if(loginMember!= null && 
				//loginMember.getPassword().equals(m.getPassword())) { 
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			//�α��� ����
			//session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember",loginMember);
			
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logoutMember.do")
//	public String logoutMember(HttpSession session) {
//		session.invalidate();
	public String logoutMember(SessionStatus session) {
		//setComplete() : model��ü�� session��ó�� ������ ���̳� session�� �ִ� �ΰ��� ���� �� ���� �� ����
		//isComplete(): session���� �ִ�? 
		if(!session.isComplete()) { //if(session!=null)�� ������
			session.setComplete();  //session�� ����
		}
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		log.debug("�Ķ���ͷ� ���޵� member : {}", m);
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
	
	@RequestMapping("duplicateId.do")
	public void duplicateId(String userId, HttpServletResponse response) throws IOException{
		
		Member m = service.selectMemberById(Member.builder().userId(userId).build());
		
		response.setContentType("application/json;charset=utf-8");
		
//		response.getWriter().print(m==null ? false : true);
		new Gson().toJson(m, response.getWriter());
	}
	
	
//	jackson���̴��� �̿��ؼ� json���� �޼ҵ� �����ϱ�
//	�޼ҵ忡 @ResponseBody������̼� ����
	
	
	@RequestMapping("/duplicateConverter.do")
	@ResponseBody
	public Member duplicateUserId(Member m) {
		Member result = service.selectMemberById(m);
		return result;
	}
	
	
	@RequestMapping("memberList.do")
	@ResponseBody
	public List<Member> selectMemberAll(){
		List<Member> list = service.selectMemberAll();
		return list;
	}
	
	@RequestMapping(value="/ajax/insert", 
			consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean insertTest(@RequestBody Member m) {
		log.debug("{}", m);
		return true;
	}
	
	@RequestMapping("/loginpage.do")
	public String loginpage() {
		return "member/loginpage";
	}
	
	
	
	
}
