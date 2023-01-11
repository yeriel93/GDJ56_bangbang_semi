package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemoDaoImpl implements MemoDao {

	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		log.debug("insertmemo����");
		return session.insert("memo.insertMemo", m);
	}

	@Override
	public List<Memo> selectMemoAll(SqlSessionTemplate session) {
		return session.selectList("memo.selectMemo");
	}
	
	@Override
	public List<Memo> selectMemoListPage(SqlSessionTemplate session, Map<String, Integer> param) {
		return session.selectList("memo.selectMemo", null, new RowBounds((param.get("cPage")-1) * param.get("numPerpage"), param.get("numPerpage")));
	} 
	
	@Override
	public int selectMemoListCount(SqlSessionTemplate session) {
		return session.selectOne("memo.selectMemoListCount");
	}

}
