package com.bs.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		return session.insert("memo.insertMemo", m);
	}

	@Override
	public List<Memo> selectMemoAll(SqlSessionTemplate session) {
		return session.selectList("memo.selectMemo");
	}

	@Override
	public int selectMemoListCount(SqlSessionTemplate session) {
		return session.selectOne("memo.selectMemoListCount");
	}

}
