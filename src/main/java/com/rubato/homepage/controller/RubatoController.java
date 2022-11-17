package com.rubato.homepage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubato.homepage.dao.IDao;

@Controller
public class RubatoController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="board_list")
	public String board_list() {
		return "board_list";
	}
	
	@RequestMapping(value="board_write")
	public String board_write() {
		return "board_write";
	}
	
	@RequestMapping(value="board_view")
	public String board_view() {
		return "board_view";
	}
	
	@RequestMapping(value="member_join")
	public String member_join() {
		return "member_join";
	}
	
	@RequestMapping(value="joinOk")
	public String joinOk(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		dao.memberJoinDao(mid, mpw, mname, memail);
		
		return "index";
	}
	
	
}
