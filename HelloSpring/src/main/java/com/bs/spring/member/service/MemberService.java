package com.bs.spring.member.service;

import java.util.List;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	
	Member selectMemberById(Member m);
	
	int insertMember(Member m);
	
	List<Member> selectMemberAll();
}
