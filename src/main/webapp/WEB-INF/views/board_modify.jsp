<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/03board_write_main.css">
</head>
<body>
<%
		String sid = (String)session.getAttribute("sessionId");
		String rfbuserid = (String)request.getAttribute("rfbuserid");
		if(sid == null){
	%>
	<script language="JavaScript">
			alert("로그인 후 이용해주세요!")
			history.go(-1);
	</script>
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
   	}else if(!sid.equals(rfbuserid)){
 	%>
   		<script language="JavaScript">
			alert("본인 글만 수정할 수 있습니다!")
			history.go(-1);
		</script>
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
            <div>
                <h2 id="write_title">글 수정</h2>
                <form action="modifyOk" method="post">
                <table >
                	<input type="hidden" name="rfbnum" value="${content.rfbnum}">
                    <tr id="name">
                        <td class="col1">이름</td>
                        <td class="col2"><input type="text" name="rfbname" value="${content.rfbname}" readonly="true"></td>
                    </tr>
                    <tr id="subject">
                        <td class="col1">제목</td>
                        <td class="col2"><input type="text" name="rfbtitle" value="${content.rfbtitle}"></td>
                    </tr>
                    <tr id="content">
                        <td class="col1">내용</td>
                        <td class="col2"><textarea name="rfbcontent">${content.rfbcontent}</textarea></td>
                    </tr>
                    <tr id="upload">
                        <td class="col1">업로드 파일</td>
                        <td class="col2"><input type="file" name="files"></td>
                    </tr>
                </table>
            </div>
            <div id="buttons">
                <input type="image" src="${pageContext.request.contextPath}/resources/img/ok.png">
                <a href="board_list"><img src="${pageContext.request.contextPath}/resources/img/list.png"></a>
            </div>
            </form>
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