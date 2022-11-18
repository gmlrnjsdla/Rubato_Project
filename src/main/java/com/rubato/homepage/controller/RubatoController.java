package com.rubato.homepage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.rubato.homepage.dto.RReplyDto;

@Controller
public class RubatoController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="index")
	public String index(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		ArrayList<RFBoardDto> dtos = dao.rfblistDao();
		model.addAttribute("list", dtos);
		
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
		ArrayList<RFBoardDto> dtos = dao.rfblistDao();
		
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
		
		ArrayList<RReplyDto> rrdtos = dao.boardReplyListDao(rfbnum);
		model.addAttribute("rrlist", rrdtos);
		
		return "board_view";
	}
	
	@RequestMapping(value="board_delete")
	public String board_delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String rfbnum = request.getParameter("rfbnum");
		
		dao.boardDeleteDao(rfbnum);
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value="board_modify")
	public String board_modify(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String rfbnum = request.getParameter("rfbnum");
		
		RFBoardDto dto = dao.boardViewDao(rfbnum);
		String rfbuserid = dto.getRfbuserid();
		
		model.addAttribute("rfbuserid", rfbuserid);
		model.addAttribute("content", dto);
		
		return "board_modify";
	}
	
	@RequestMapping(value="modifyOk")
	public String modifyOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String rfbnum = request.getParameter("rfbnum");
		String rfbtitle = request.getParameter("rfbtitle");
		String rfbcontent = request.getParameter("rfbcontent");
		
		dao.boardModifyDao(rfbtitle, rfbcontent, rfbnum);
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value="replyOk")
	public String replyOk(HttpServletRequest request, Model model, HttpServletResponse response) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sessionId");
		
		if(sid == null) {//참이면 로그인이 안된 상태
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('로그인하지 않으면 글을 쓰실수 없습니다!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			String rrid = request.getParameter("rrid");
			String rrcontent = request.getParameter("rrcontent");
			String rrorinum = request.getParameter("rrorinum");
			
			dao.boardReplyDao(rrcontent, rrid, rrorinum);
			RFBoardDto dto = dao.boardViewDao(rrorinum);
			
			model.addAttribute("content", dto);
			
			ArrayList<RReplyDto> rrdtos = dao.boardReplyListDao(rrorinum);
			model.addAttribute("rrlist", rrdtos);
		}
		return "board_view";
	}
	
}
