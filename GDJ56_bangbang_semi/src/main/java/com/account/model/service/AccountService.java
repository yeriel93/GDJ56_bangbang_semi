package com.account.model.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.commit;
import static com.bangbang.common.JDBCTemplate.getConnection;
import static com.bangbang.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.account.model.dao.AccountDao;
import com.account.model.vo.Alert;
import com.user.model.vo.User;

public class AccountService {
	private static AccountService accountService;
	private AccountService() {}
	public static AccountService getAccountService() {
		if(accountService == null) accountService = new AccountService();
		return accountService;
	}
	
	public int updateUser(User u) {
		Connection conn=getConnection();
		int result=AccountDao.getAccountDao().updateUser(conn,u);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updatePw(String userId,String afterPw) {
		Connection conn=getConnection();
		int result=AccountDao.getAccountDao().updatePw(conn,userId,afterPw);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int sendMessage(Alert a) {
		Connection conn=getConnection();
		int result=AccountDao.getAccountDao().sendMessage(conn,a);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
}
