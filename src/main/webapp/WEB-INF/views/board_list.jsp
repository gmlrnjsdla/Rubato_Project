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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/02board_list_main.css">
</head>
<body>
  <div id="wrap">
    <header>    <!--header strat-->
        <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/img/logo.png"></a>
        <nav id="top_menu">
            HOME | LOGIN | JOIN | NOTICE
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
                <ul id="login_input">
                    <li><input type="text"></li>
                    <li><input type="password"></li>
                </ul>
                <img id="login_btn" src="${pageContext.request.contextPath}/resources/img/btn_login.gif">
            </div>
            <div class="clear"></div>
            <div id="join_search">
                <a href="member_join"> <img src="${pageContext.request.contextPath}/resources/img/btn_join.gif"></a>
                <img src="${pageContext.request.contextPath}/resources/img/btn_search.gif">
            </div>
        </article>  <!-- login box end -->

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
            <div id="total_search">
                <div id="total">
                    ▷ 총 5개의 게시물이 있습니다.
                </div>
                <div id="search">
                    <div id="select_img">
                        <img src="${pageContext.request.contextPath}/resources/img/select_search.gif">
                    </div>
                    <div id="search_select">
                        <select>
                            <option>제목</option>
                            <option>내용</option>
                            <option>글쓴이</option>
                        </select>
                    </div>
                    <div id="search_input">
                        <input type="text">
                    </div>
                    <div id="search_btn">
                        <img src="${pageContext.request.contextPath}/resources/img/search_button.gif">
                    </div>
                </div>
            </div>      <!-- total search end -->
            <table>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>일시</th>
                    <th>조회수</th>
                </tr>
                <tr>
                    <td class="col1">1</td>
                    <td class="col2"><a href="board_view"> 까스통님의 선물인 보드카가 정말 독하네요!!!</a></td>
                    <td class="col3">루바토</td>
                    <td class="col4">2022-09-30</td>
                    <td class="col5">15</td>
                </tr>
                <tr>
                    <td class="col1">2</td>
                    <td class="col2"><a href="board_view"> 까스통님의 선물인 보드카가 정말 독하네요!!!</a></td>
                    <td class="col3">루바토</td>
                    <td class="col4">2022-09-30</td>
                    <td class="col5">15</td>
                </tr>
                <tr>
                    <td class="col1">3</td>
                    <td class="col2"><a href="board_view"> 까스통님의 선물인 보드카가 정말 독하네요!!!</a></td>
                    <td class="col3">루바토</td>
                    <td class="col4">2022-09-30</td>
                    <td class="col5">15</td>
                </tr>
            </table>        <!-- 게시판 목록 테이블 end -->
            <div id="buttons">
                <div class="col1">
                    ◀이전 1 다음▶
                </div>
                <div class="col2">
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/img/list.png"></a>
                    <a href="board_write"><img src="${pageContext.request.contextPath}/resources/img/write.png"></a>
                </div>
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