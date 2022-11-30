package com.rubato.homepage.dao;

import java.util.ArrayList;

import com.rubato.homepage.dto.Criteria;
import com.rubato.homepage.dto.FileDto;
import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RMemberDto;
import com.rubato.homepage.dto.RReplyDto;

public interface IDao {
	
	public void memberJoinDao(String mid, String mpw, String mname, String memail);
	public int checkUserIdDao(String mid);
	public String loginOkDao(String mid);
	public RMemberDto memberInfoDao(String mid);
//======================================회원 관련=========================================//
	
	
	
	
	public void rfbWriteDao(String rfbname, String rfbtitle, String rfbcontent, String rfbuserid, int filecount);
	public ArrayList<RFBoardDto> rfblistDao(Criteria cri);
	public RFBoardDto boardViewDao(String rfbnum);
	public void uphitDao(String rfbnum);
	public void boardDeleteDao(String rfbnum);
	public void boardModifyDao(String rfbtitle, String rfbcontent, String rfbnum);
	
	public ArrayList<RFBoardDto> rfbSearchTitleList(String searchKey);
	public ArrayList<RFBoardDto> rfbSearchContentList(String searchKey);
	public ArrayList<RFBoardDto> rfbSearchWriterList(String searchKey);
//======================================게시판 관련=========================================//	
	
	
	
	
	public void boardReplyDao(String rrcontent, String rrid, String rrorinum);
	public ArrayList<RReplyDto> boardReplyListDao(String rrorinum);
	public void boardReplyCountDao(String rfbnum);
	public void replyDeleteDao(String rrnum);
	public void replyDeleteCountDao(String rfbnum);
	public int boardAllCount();

//======================================댓글 관련=========================================//
	

	
	public void fileInfoInsert(int boardnum, String fileoriname, String filename, String fileextension, String fileurl);
	public ArrayList<RFBoardDto> boardLatestInfoDao(String rfbuserid);
	public FileDto getfileInfoDao(String rfbnum);
	public void fileCountDao(int boardnum);
//======================================파일 관련=========================================//
}
