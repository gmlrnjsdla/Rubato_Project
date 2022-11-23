<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>클래식기타 커뮤니티</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/01common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/01header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/01main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/01footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/02board_left.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/04board_view_main.css">

	<style>
		table{
			border:1px solid #dddddd;
		}
	</style>
</head>
<body>
<%
		String sid = (String)session.getAttribute("sessionId");
		if(sid == null){
	%>
  <div id="wrap">
    <header>    <!--header strat-->
        <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/img/logo.png"></a>
        <nav id="top_menu">
            <a href="index">HOME</a> |
            <a href="index">LOGIN</a> |
            <a href="member_join">JOIN</a> | 
            <a href="#">NOTICE</a>
        </nav>
        <nav id="main_menu">
            <ul>
                <li><a href="board_list">자유 게시판</a></li>
                <li><a href="#">기타 연주</a></li>
                <li><a href="#">공동 구매</a></li>
                <li><a href="#">연주회 안내</a></li>
                <li><a href="#">회원 게시판</a></li>
            </ul>
        </nav>
    </header>   <!--header end-->
    <aside>     <!-- login box strat -->
        <article id="login_box">
            <img id="login_title" src="${pageContext.request.contextPath}/resources/img/ttl_login.png">
            <div id="input_button">
            <form action="loginOk" method="post">
                <ul id="login_input">
                    <li><input type="text" name="mid"></li>
                    <li><input type="password" name="mpw"></li>
                </ul>
                <input type="image" id="login_btn" src="${pageContext.request.contextPath}/resources/img/btn_login.gif">
            </form>
            </div>
            <div class="clear"></div>
            <div id="join_search">
                <a href="member_join"><img src="${pageContext.request.contextPath}/resources/img/btn_join.gif"></a>
                <img src="${pageContext.request.contextPath}/resources/img/btn_search.gif">
            </div>
        </article>  <!-- login box end -->
    
   	<%
   	}else{
 	%>
 	<div id="wrap">
    <header>    <!--header strat-->
        <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/img/logo.png"></a>
        <nav id="top_menu">
            <a href="index">HOME</a> |
            <a href="logout">LOGOUT</a> |
            <a href="member_join">JOIN</a> | 
            <a href="#">NOTICE</a>
        </nav>
        <nav id="main_menu">
            <ul>
                <li><a href="board_list">자유 게시판</a></li>
                <li><a href="#">기타 연주</a></li>
                <li><a href="#">공동 구매</a></li>
                <li><a href="#">연주회 안내</a></li>
                <li><a href="#">회원 게시판</a></li>
            </ul>
        </nav>
    </header>   <!--header end-->
 	<aside>
 		<article id="login_box">
 			<img id="login_title" src="${pageContext.request.contextPath}/resources/img/ttl_login.png">
            <div id="input_button">
            <br>
            <h3><%=sid %>님 안녕하세요!</h3>
            <br>
            <a href="logout"><h3>LOGOUT</h3></a>
            </div>
        </article>  <!-- login box end -->
	     
	<%
   	}
	%>
        <nav id="sub_menu">     <!-- sub_menu start -->
            <ul>
                <li><a href="board_list">+ 자유게시판</a></li>
                <li><a href="#">+ 방명록</a></li>
                <li><a href="#">+ 공지사항</a></li>
                <li><a href="#">+ 등업 요청</a></li>
                <li><a href="#">+ 포토갤러리</a></li>
            </ul>
        </nav>      <!-- sub_menu end -->
        <article id="sub_banner">
            <ul>
                <li><img src="${pageContext.request.contextPath}/resources/img/banner1.png"></li>
                <li><img src="${pageContext.request.contextPath}/resources/img/banner2.png"></li>
                <li><img src="${pageContext.request.contextPath}/resources/img/banner3.png"></li>
            </ul>
        </article>
    </aside>
    <main>
        <section id="main">
            <img src="${pageContext.request.contextPath}/resources/img/comm.gif">
            <h2 id="board_title">자유 게시판</h2>
            <div id="view_title_box">
                <span id="boardTitle">${content.rfbtitle}</span>
                <span id="info">
                	${content.rfbname} | 조회수 : ${content.rfbhit} | ${content.rfbdate}
                </span>
            </div>
            <P id="view_content">
                ${content.rfbcontent}<br><br><br>
            </P>
            
            <c:forEach items="${rrlist}" var="rrlist">
            <table border="1" cellpadding="0" cellspacing="0" width="760">
            	<tr align="center" bgcolor="#dddddd">
            		<td width="50">${rrlist.rrid}</td>
            		<td width="500" align="left" style="padding-left:20px;">${rrlist.rrcontent}</td>
            		<td>${rrlist.rrdate}</td>
            		<td style="padding-top:5px;"><a href="replyDelete?rrnum=${rrlist.rrnum}&rrorinum=${rrlist.rrorinum}&rrid=${rrlist.rrid}"><img src="${pageContext.request.contextPath}/resources/img/delete.png" ></a></td>
            	</tr>
            </table>
                            
            </c:forEach>
            
            
            <form action="replyOk">
            <div id="comment_box">
            	<input type="hidden" name="rrid" value="<%=sid%>">
            	<input type="hidden" name="rrorinum" value="${content.rfbnum}">
                <img id="title_comment" src="${pageContext.request.contextPath}/resources/img/title_comment.gif">
                <textarea name="rrcontent"></textarea>
                <input type="image" id="ok_ripple" src="${pageContext.request.contextPath}/resources/img/ok_ripple.gif">
            </div>
            </form>
            
            <div id="buttons">
                <a href="board_delete?rfbnum=${content.rfbnum}&rfbuserid=${content.rfbuserid}" onclick="return confirm('삭제하면 다시 복구할 수 없습니다.\n정말 삭제하시겠습니까?')">
                	<img src="${pageContext.request.contextPath}/resources/img/delete.png">
                </a>
                <a href="board_list"><img src="${pageContext.request.contextPath}/resources/img/list.png"></a>
                <a href="board_modify?rfbnum=${content.rfbnum}"><img src="${pageContext.request.contextPath}/resources/img/modify.png"></a>
            </div>
        </section>
    </main>
    <div class="clear"></div>

    <footer>    <!-- footer strat -->
        <img id="footer_logo" src="${pageContext.request.contextPath}/resources/img/footer_logo.gif">
        <ul id="address">
            <li>서울시 강남구 삼성동 1234 우 : 123-1234</li>
            <li>TEL : 031-123-1234 Email : email@domain.com</li>
            <li id="copyright">COPYRIGHT (C) 루바토 ALL RIGHTS RESERVED</li>
        </ul>
        <ul id="footer_sns">
            <img src="${pageContext.request.contextPath}/resources/img/facebook.gif">
            <img src="${pageContext.request.contextPath}/resources/img/blog.gif">
            <img src="${pageContext.request.contextPath}/resources/img/twitter.gif">
        </ul>
    </footer>   <!-- footer end -->
  </div>
</body>
</html>