<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("UTF-8");
%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>회원정보 수정</h2>
	<form name="frmMember" action="${contextPath}/memb/modMember.do?gid=${memInfo.id}" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${memInfo.id}" disabled></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" value="${memInfo.pwd}"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${memInfo.name}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email">
					@
					<select name="edomain">
						<option value="daum.net">daum.net</option>
						<option value="naver.com">naver.com</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					등산 : <input type="checkbox" name="hobby" value="등산"> 
					독서 : <input type="checkbox" name="hobby" value="독서">
					낚시 : <input type="checkbox" name="hobby" value="낚시">
				</td>
			</tr>
		</table>
		<input type="submit" value="수정하기">
	</form>

</body>
</html>