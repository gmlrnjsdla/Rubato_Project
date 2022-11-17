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
                <img src="${pageContext.request.contextPath}/resources/img/btn_join.gif">
                <img src="${pageContext.request.contextPath}/resources/img/btn_search.gif">
            </div>
        </article>  <!-- login box end -->
        <article id="guestbook">    <!-- guestbook start -->
            <div id="guestbook_title">
                <img src="${pageContext.request.contextPath}/resources/img/ttl_memo.gif">
            </div>
            <ul>
                <li>안녕하세요!</li>
                <li>안녕하세요!</li>
                <li>안녕하세요!</li>
                <li>안녕하세요!</li>
            </ul>
        </article>
    </aside>        <!-- guestbook end -->

    <main>
        <section id="main">
            <img src="${pageContext.request.contextPath}/resources/img/main_img.png">
            <section id="notice_free_youtube">  <!-- 공지사항/자유게시판/유튜브 start -->
                <article id="notice">   <!-- 공지사항 -->
                    <div class="latest_title">
                        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest1.gif">
                        <img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
                        <div class="clear"></div>
                    </div>
                    <div class="latest_content">
                        <img class="image" src="${pageContext.request.contextPath}/resources/img/book_pen.gif">
                        <ul class="list">
                            <li>
                                <div class="subject">루바토 개편과 사이트 이용...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">루바토 개편과 사이트 이용...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">루바토 개편과 사이트 이용...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">루바토 개편과 사이트 이용...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                        </ul>
                    </div>
                </article>      <!-- 공지사항 끝 -->

                <article id="freeBoard">    <!-- 자유게시판 -->
                    <div class="latest_title">
                        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest2.gif">
                        <img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
                        <div class="clear"></div>
                    </div>
                    <div class="latest_content">
                        <img class="image" src="${pageContext.request.contextPath}/resources/img/book_pen.gif">
                        <ul class="list">
                            <li>
                                <div class="subject">까스통님의 선물인 보드카...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">까스통님의 선물인 보드카...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">까스통님의 선물인 보드카...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <div class="subject">까스통님의 선물인 보드카...</div>
                                <div class="date">2022-09-30</div>
                                <div class="clear"></div>
                            </li>
                        </ul>
                    </div>
                </article>      <!-- 자유게시판 끝 -->
                <article id="youtube">      <!-- 유튜브 -->
                    <div class="latest_title">
                        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest3.gif">
                        <img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
                        <div class="clear"></div>
                    </div>
                    <img id="youtube_img" src="${pageContext.request.contextPath}/resources/img/bach.jpg">
                </article>      <!-- 유튜브 끝 -->
            </section>      <!-- 공지사항/자유게시판/유튜브 end -->

            <section id="gallery">      <!-- gallery start -->
                <img src="${pageContext.request.contextPath}/resources/img/latest4.gif">
                <div id="gallery_box">      <!-- gallery_box start -->
                    <div id="gallery_list">     <!-- gallery_list start -->
                        <div class="items">
                            <ul>
                                <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
                                <li>기타 페스티벌 4중주</li>
                            </ul>
                        </div>
                        <div class="items">
                            <ul>
                                <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
                                <li>기타 페스티벌 4중주</li>
                            </ul>
                        </div>
                        <div class="items">
                            <ul>
                                <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
                                <li>기타 페스티벌 4중주</li>
                            </ul>
                        </div>
                    </div>      <!-- gallery_list start -->
                </div>      <!-- gallery_box end -->
            </section>      <!-- gallery end -->
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