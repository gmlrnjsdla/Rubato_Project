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
		String mpw = (String)request.getAttribute("mpw");
		String dbpw = (String)request.getAttribute("dbpw");
		
		if(dbpw != null){
			if(!dbpw.equals(mpw)) {
	%>
				<script language="JavaScript">
					alert("비밀번호가 틀렸습니다. 다시 확인해주세요.")
					history.go(-1);
				</script>
	<%	
			}
			
		}
		else{
	%>
			<script language="JavaScript">
					alert("없는 아이디 입니다. 다시 확인해주세요.")
					history.go(-1);
				</script>
	<%
		}
	%>
</body>
</html>