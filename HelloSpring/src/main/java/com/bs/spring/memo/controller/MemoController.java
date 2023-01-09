package com.bs.spring.memo.controller;

import java.util.List;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {

	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service = service;
	}
	
	//get������� �������� �޼ҵ带 �����ϰڴٴ� ��
	@RequestMapping(value="/memoList.do", method= {RequestMethod.GET})
	public ModelAndView memoList(ModelAndView mv) {
		mv.addObject("memos",service.selectMemoAll());
		
		//����¡ó���ϱ�
		int totalData = service.selectMemoListCount();
		mv.addObject(PageFactory.getPage(totalData, totalData, totalData, null));
		
		
		
		
		
		mv.setViewName("memo/memoList");
		return mv;
	}
	
	@RequestMapping("/insertMemo.do")
	public String Memo() {
		return "memo/insertMemo";
	}
	
	@RequestMapping("/insertMemoEnd.do")
	public ModelAndView insertMemo(Memo m, ModelAndView mv) {
		log.debug("{}",m);
		
		if(service.insertMemo(m)>0) {
			mv.addObject("msg","�޸� ��� �Ϸ�");
			mv.addObject("loc","/memo/memoList.do");
		}else {
			mv.addObject("msg","�޸��� ����");
			mv.addObject("loc","/memo/insertMemo.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
}
