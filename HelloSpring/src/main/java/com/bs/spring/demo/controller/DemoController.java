package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	@Autowired 
	private DemoService service;
	
	
	public DemoController(DemoService service) {
		this.service = service;
	}
	
	//Ŭ���̾�Ʈ�� ��û�� ���񽺸� �������ִ� ���
	//Ŭ���̾�Ʈ�� ��û�� ���� �ּ�(URL)�� �´� �޼ҵ带 ����
	//�޼ҵ屸�� �Ҷ� �����ּҿ� �������ִ� @������̼��� ����
	//@RequestMapping, @GetMapping, @PostMapping ��� @������̼��� ���
	//@RequestMapping(�����ּ�[,�߰� �ɼǼ���]) - Rest����� ���� ������ ���
	
	//demo�������� �����ϴ� �޼ҵ� ����
	//��ȯ���� String -> SpringBean���� ��ϵ� ViewResolver���� �������� ������ ��ġ���� ã�� ����
	//�Ϲ������� �Ű������� ����
	//�޼ҵ���� �˾Ƽ�..�� 
	@RequestMapping("/demo/demo.do")
	public String demoPage() {
		//InternalResourceViewResolver�� ó����
		//InternalResourceViewResolver�� ����� prefix, suffix�� ������ ��ȯ���̶� �����ؼ� viewȭ���� ã��
		//prefix : /WEB-INF/views/
		//suffix: .jsp
		return "demo/demo";  // /WEB_INF/views/demo/demo.jsp -> RequestDispatcher("/WEB-INF/views/demo/demo.jsp").forward(request.response)�� ������.
	}
	
	//��û���θ޼ҵ��� �Ű������� ��ȯ��
	//1. ��ȯ��
	// String : ViewResolver�� ���ؼ� viewȭ���� ��ȯ�Ҷ� ���
	// void : HttpResponse�� ���� ����޼����� �ۼ��Ҷ� ���(upload, download...)
	// ModelAndView : ModelAndView��ü�� ��ȯ(ȭ������, view�� ������ ������)
	// Ŭ����Ÿ��(RefferenceType) : ������ ��ü�� ��ȯ -> json������� ��ȯ
	
	//2. �Ű����� -> Spring�� �˾Ƽ� �־���!
	// HttpServletRequest : �����Ҷ� ��� ������ �׳༮..!
	// HttpServletResponse : �����Ҷ� ��� ������ �׳༮..!
	// HttpSession : Session�� �׳༮!
	// java.util.Locale : ��ǰ� �ִ� ������ ��������
	// inputStream/Reader : ���� IO�Ҷ� ����ϴ� stream��ü
	// outputStream/Writer : ���� IO�Ҷ� ����ϴ� stream��ü
	// �⺻�ڷ��� ���� : Ŭ���̾�Ʈ�� ���� name��Ī�� �������� ��ġ�ϸ� ��������!
				// ����!! �⺻�ڷ��� ������ ������������ �ݵ�� ��� ������ ����Ǵ� ���� �����ؾ���(@RequestParam������̼��� ������� �ʰ� �⺻�ڷ��� ������ ��������)
				// name�� ��ġ���� �ʴ� �������� ����������� @RequestParam������̼��� �̿��ؼ� mapping�� �� ���� 
				// �߰����� ������ �ʿ��Ҷ��� @RequestParam������̼��� ����� �� ����.
	
				//�⺻ �ڷ��� ���� ����� @RequestHeader, @CookieValue ������̼��� �̿��ϸ� header�� cookie�� ���� �ٷ� ������ �� ����
	// Ŭ����(RefferenceType) ���� : command��� �ϰ� Ŭ���̾�Ʈ�� ���� �����͸� ������ Ÿ���� Ŭ������ �����ؼ� ��������.
							//����) �ʵ��� Ŭ���̾�Ʈ�� ���� name���� ��ġ�ϴ� ��
	// java.util.Map : Ŭ���̾�Ʈ�� ���� �����͸� map���� ��������.
	// Model : �������� �����͸� �����ϴ� �뵵�� ��ü /1ȸ�� ������ ����
	// ModelAndView : ������ ������, ȭ�� ������ �ѹ��� �����ϴ� ��ü 1ȸ��
	
	//�߰� @������̼�
	// @ResponseBody -> json���·� ��ȯ�Ҷ� ���
	// @RequestBody -> json���·� ���޵� �����͸� vo��ü�� mapping���ִ� ��
	
	//����ó�� �̿��ϱ�
	@RequestMapping("/demo/demo1.do")
//	public String demo1(HttpServletRequest req, HttpServletResponse res) {
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		System.out.println(req);
		System.out.println(res);
		
		String name = req.getParameter("devName");
		int age = Integer.parseInt(req.getParameter("devAge"));
		String gender = req.getParameter("devGender");
		String email = req.getParameter("devEmail");
		String[] devlang = req.getParameterValues("devLang");
		System.out.println(name+age+gender+email+Arrays.toString(devlang));
		
		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(email).devLang(devlang).build();
		
//		req.setAttribute("demo", d);
//		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req,res);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<h1>������<h1>");
		
//		return "demo/demoResult";
	}
	
	
	//�⺻�ڷ������� ������ �޾ƿ���
	@RequestMapping("demo/demo2.do")
	public String basicType(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model model) {
		System.out.println(devName+" "+devAge+" "+devEmail+" "+devGender);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		//model��ü�� ������ �����ϱ� 
		//key,value�������� �����͸� ������
		//addAttribute()�޼ҵ带 �̿�
		model.addAttribute("demo",d);
		
		
		return "demo/demoResult";
	}
	
	//@RequestParam����ϱ�
	@RequestMapping("demo/demo3.do")
	public String requestparamTest(@RequestParam(value="devName")String name, 
			@RequestParam(value="devAge", defaultValue = "1")int age, //����ڰ� ���� �Է����� �ʾ����� default���� ���� ���� defaultValue =""(String Ÿ��)
			@RequestParam(value="devEmail" )String email,  
			@RequestParam(value="devGender")String gender,
			@RequestParam(value="devLang", required = false)String[] lang,  //����ڰ� ���� �Է����� �ʾƵ� �������� �ʰ� �׳� �Ѿ���� ���� required=false
			Model model) {
		
		System.out.println(name+age+gender+email);
		if(lang!=null) {
			for(String l : lang) {
				System.out.println(l);
			}
		}
		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(email).devLang(lang).build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}

	//vo��ü�� �ٷ� �ޱ�
	//�ʵ忡 �ڷ������� �⺻�ڷ����鸸 ���� (java.util.Date��� ��üŸ���� �ȵ� /�ٵ� java.sql.Date�� ������ )
	@RequestMapping("demo/demo4.do")
	public String commandMappingTest(Demo demo, Model model) {
		System.out.println(demo);
		model.addAttribute("demo", demo);
		return "demo/demoResult";
	}
	
	
	//Map��ü�� �ٷ� �ޱ�
	//�Ű����� Map�� @RequestParam �̰� �� ����� ��! 
	//���ϰ��� �������� ������ �迭�� ���� �Ѱ�����Ѵ�.
	@RequestMapping("demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param, String[] devLang, Model model) {
		System.out.println(param);
		param.put("devLang", devLang);
		model.addAttribute("demo", param);
		return "demo/demoResult";
	}
	
	
	//��Ű, ���� �� �޾ƿ���
	@RequestMapping("demo/demo6.do")
	public String setraTest(@CookieValue(value="testData", required = false) String testData,	@RequestHeader(value="User-agent") String userAgent,
							@SessionAttribute(value="sessionId") String id, @RequestHeader(value="Referer") String referer) {
		System.out.println(testData);
		System.out.println(userAgent);
		System.out.println(id);
		System.out.println(referer);
		
		return "demo/demoResult";
	}
	
	
	//ModelAndView�� ��ȯ�ϱ�
	@RequestMapping("/demo/demo7.do")
	public ModelAndView moderlAndViewTest(ModelAndView mv, Demo demo) {
		//ModelAndView��ü�� view������, ������ ������ ���� �� �� �ִ� ��ü
		//data���� : addObject("key", value) �޼ҵ� �̿�
		//view���� : setViewName("view�̸�")�޼ҵ� �̿�
		mv.addObject("demo", demo);
		mv.setViewName("demo/demoResult");
		
		return mv;
	}
	
	
//	restful�ϰ� ���񽺸� �����Ҷ� ��� -> @RestController�� ����ϰ� ��.
//	@GetMapping : ��ȸ
//	@PostMapping : ���� 
//	@PutMapping : ����
//	@DeleteMapping : ����
	
//	@PathVariable
	
	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	//@ResponseBody�� ����ϸ� �׳� ��ȯ���� �׳� �����ͷ� �������� 
	public String responseTest() {
//	public List<String> responseTest() {
		
		return "hello responseBody";
//		return List.of("1","2","3","4");
	}
	
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		int result = service.insertDemo(demo);
		//spring���� redirectó���ϱ� (�޼ҵ� ���� �� ������ ��ȯ)
		return "redirect:/demo/demo.do";
	}
	
	
	@RequestMapping("/demo/selectDemoList.do")
	public ModelAndView selectDemoList(ModelAndView mv) {
		List<Demo> list = service.selectDemoList();
//		System.out.println(list);
		mv.addObject("demos",list);
		mv.setViewName("demo/demoList");
		return mv;
	}
	
	
	
	
}
