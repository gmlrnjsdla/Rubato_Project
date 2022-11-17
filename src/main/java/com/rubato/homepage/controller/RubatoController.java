package com.rubato.homepage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rubato.homepage.dao.IDao;
import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RMemberDto;

@Controller
public class RubatoController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="index")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value="member_join")
	public String member_join() {
		return "member_join";
	}
	
	@RequestMapping(value="joinOk", method = RequestMethod.POST)
	public String joinOk(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		dao.memberJoinDao(mid, mpw, mname, memail);
		
		return "redirect:index";
	}
	
	@RequestMapping(value="checkUserId", method = RequestMethod.POST)
	public String checkUserId(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String mid = request.getParameter("mid");
		
		int count = dao.checkUserIdDao(mid);
		model.addAttribute("count", count);
		
		return "checkUserId";
	}
	
	@RequestMapping(value="loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String dbpw = dao.loginOkDao(mid);
		model.addAttribute("dbpw", dbpw);
		model.addAttribute("mpw", mpw);
		
		if(dbpw != null) {
			if(dbpw.equals(mpw)) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionId", mid);
				
				return "redirect:index";
			}
			else{
				return "loginOk";
			}
		}
		else{
			return "loginOk";
		}
	}
	
	@RequestMapping(value="logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:index";
	}
	
	@RequestMapping(value="board_write")
	public String board_write(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sessionId");
		
		if(sid != null) {
			RMemberDto dto = dao.memberInfoDao(sid);
			model.addAttribute("minfo", dto);
		}
		
		return "board_write";
	}
	
	@RequestMapping(value="writeOk", method = RequestMethod.POST)
	public String writeOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String rfbuserid = request.getParameter("rfbuserid");
		String rfbname = request.getParameter("rfbname");
		String rfbtitle = request.getParameter("rfbtitle");
		String rfbcontent = request.getParameter("rfbcontent");
		
		dao.rfbWriteDao(rfbname, rfbtitle, rfbcontent, rfbuserid);
		
		return "redirect:board_list";
	}
	
	
	@RequestMapping(value="board_list")
	public String board_list(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<RFBoardDto> dtos = dao.listDao();
		
		model.addAttribute("list", dtos);
		
		int boardCount = dtos.size();
		model.addAttribute("boardCount", boardCount);
		
		return "board_list";
	}
	
	@RequestMapping(value="board_view")
	public String board_view(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String rfbnum = request.getParameter("rfbnum");
		
		dao.uphitDao(rfbnum);
		RFBoardDto dto = dao.boardViewDao(rfbnum);
		
		model.addAttribute("content", dto);
		
		return "board_view";
	}
	
}
