package com.smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class userDAO {
	
	SqlSessionFactory factory = SqlSessionManager.getSqlSession();
	
	public void Login(userVO vo) {
		
		SqlSession session = factory.openSession(true);
		
		int result = session.insert("sign", vo);
	}
}
