package com.rubato.homepage.dao;

import com.rubato.homepage.dto.RMemberDto;

public interface IDao {
	
	public void memberJoinDao(String mid, String mpw, String mname, String memail);
	public int checkUserIdDao(String mid);
	public String loginOkDao(String mid);
	
}
