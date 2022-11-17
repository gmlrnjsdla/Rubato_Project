package com.rubato.homepage.dao;

import java.util.ArrayList;

import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RMemberDto;

public interface IDao {
	
	public void memberJoinDao(String mid, String mpw, String mname, String memail);
	public int checkUserIdDao(String mid);
	public String loginOkDao(String mid);
	public RMemberDto memberInfoDao(String mid);
	
	
	
	
	public void rfbWriteDao(String rfbname, String rfbtitle, String rfbcontent, String rfbuserid);
	public ArrayList<RFBoardDto> listDao();
	public RFBoardDto boardViewDao(String rfbnum);
	public void uphitDao(String rfbnum);
}
