<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int count = Integer.parseInt(request.getAttribute("count").toString());
		if(count == 0){
	%>
			<script language="JavaScript">
					alert("사용가능한 아이디입니다!!")
					history.go(-1);
			</script>
	<%
		}else{
	%>
			<script language="JavaScript">
					alert("이미 사용중인 아이디입니다.")
					history.go(-1);
			</script>	
	<%
		}
	%>
</body>
</html>