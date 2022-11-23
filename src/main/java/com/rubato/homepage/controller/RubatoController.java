package com.rubato.homepage.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.rubato.homepage.dao.IDao;
import com.rubato.homepage.dto.FileDto;
import com.rubato.homepage.dto.RFBoardDto;
import com.rubato.homepage.dto.RMemberDto;
import com.rubato.homepage.dto.RReplyDto;

@Controller
public class RubatoController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="/")
	public String home(Model model) {
		return "redirect:index";
	}
	
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
	public String writeOk(HttpServletRequest request, Model model, @RequestPart MultipartFile files) throws IllegalStateException, IOException {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sessionId");
		
		String rfbuserid = request.getParameter("rfbuserid");
		String rfbname = request.getParameter("rfbname");
		String rfbtitle = request.getParameter("rfbtitle");
		String rfbcontent = request.getParameter("rfbcontent");
		
		if(files.isEmpty()) { //파일의 첨부여부 확인
			dao.rfbWriteDao(rfbname, rfbtitle, rfbcontent, rfbuserid, 0);
		} else {
			dao.rfbWriteDao(rfbname, rfbtitle, rfbcontent, rfbuserid, 1);
			ArrayList<RFBoardDto> latestdtos = dao.boardLatestInfoDao(sid);
			RFBoardDto dto = latestdtos.get(0);
			int rfbnum = dto.getRfbnum();
			
			
			//파일 첨부
			String fileoriname = files.getOriginalFilename(); //첨부된 파일의 원래 이름
			String fileextension = FilenameUtils.getExtension(fileoriname).toLowerCase();
			//첨부된 파일의 확장자 추출 후 소문자로 강제 변경
			File destinationFile; //java.io 패키지 제공 클래스 임포트
			String destinationFileName; //실제 서버에 저장된 파일의 변경된 이름이 저장될 변수 선언
			String fileurl = "C:/SpringBoot_Workspace/Rubato_Project/src/main/resources/static/uploadfiles/";
			// 반드시 마지막에 / 붙이기!!!!!!!!!!
			//첨부된 파일이 저장될 서버의 실제 폴더 경로
			
			do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileextension;
			// 알파벳 대소문자와 숫자를 포함한 랜덤 32자 문자열 생성 후 . 을 구분자로 원본파일의 확장자를 연결 -> 실제 서버에 저장될 파일의 이름
			destinationFile = new File(fileurl + destinationFileName);
			}while(destinationFile.exists());
			
			
			destinationFile.getParentFile().mkdir();
			files.transferTo(destinationFile);
			
			dao.fileInfoInsert(rfbnum, fileoriname, destinationFileName, fileextension, fileurl);
			}
		
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
		
		FileDto fdto = dao.getfileInfoDao(rfbnum);
		if(fdto !=null) {
			String fileoriname = fdto.getFileoriname();
			String filename = fdto.getFilename();
			String fileextension = fdto.getFileextension();
			model.addAttribute("filename", filename);
			model.addAttribute("fileoriname", fileoriname);
			model.addAttribute("fileextension", fileextension);
		}
		return "board_view";
	}
	
	@RequestMapping(value="board_delete")
	public String board_delete(HttpServletRequest request, HttpServletResponse response) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String rfbnum = request.getParameter("rfbnum");
		
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sessionId");
		String rfbuserid = request.getParameter("rfbuserid");
		
		if(sid == null) {//참이면 로그인이 안된 상태
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('로그인하지 않으면 글을 삭제할 수 없습니다!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(!sid.equals(rfbuserid)){
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('본인 글만 삭제할 수 있습니다!!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			dao.boardDeleteDao(rfbnum);
		}
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
			dao.boardReplyCountDao(rrorinum);
			
			
			model.addAttribute("content", dto);
			
			ArrayList<RReplyDto> rrdtos = dao.boardReplyListDao(rrorinum);
			model.addAttribute("rrlist", rrdtos);
			
			FileDto fdto = dao.getfileInfoDao(rrorinum);
			if(fdto !=null) {
				String fileoriname = fdto.getFileoriname();
				String filename = fdto.getFilename();
				String fileextension = fdto.getFileextension();
				model.addAttribute("filename", filename);
				model.addAttribute("fileoriname", fileoriname);
				model.addAttribute("fileextension", fileextension);
			}
		}
		return "board_view";
	}
	
	@RequestMapping(value="replyDelete")
	public String replyDelete(HttpServletRequest request, Model model, HttpServletResponse response) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("sessionId");
		String rrid = request.getParameter("rrid");
		String rrnum = request.getParameter("rrnum");
		String rrorinum = request.getParameter("rrorinum");
		
		if(sid == null) {//참이면 로그인이 안된 상태
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('로그인하지 않으면 글을 삭제할 수 없습니다!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(!sid.equals(rrid)){
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				out.println("<script>alert('본인만 삭제할 수 있습니다!!');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else {
			dao.replyDeleteDao(rrnum);
			dao.replyDeleteCountDao(rrorinum);
			
			RFBoardDto dto = dao.boardViewDao(rrorinum);
			ArrayList<RReplyDto> rrdtos = dao.boardReplyListDao(rrorinum);
			
			model.addAttribute("content", dto);
			model.addAttribute("rrlist", rrdtos);
		}
		
		return "board_view";
	}
	
	
	
	@RequestMapping(value="search_list")
	public String search_list(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String searchOption = request.getParameter("searchOption");
		String searchKey = request.getParameter("searchKey");
		
		ArrayList<RFBoardDto> dtos = null;
		
		if(searchOption.equals("title")) {
			dtos = dao.rfbSearchTitleList(searchKey);
		}else if(searchOption.equals("content")) {
			dtos = dao.rfbSearchContentList(searchKey);
		}else {
			dtos = dao.rfbSearchWriterList(searchKey);
			model.addAttribute("list", dtos);
		}
		
		int boardCount = dtos.size();
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("list", dtos);
		return "board_list";
	}
	
}
