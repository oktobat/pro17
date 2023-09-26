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
	
	<h2>회원정보</h2>
	<table border="1" width="800" align="center">
		<tr align="center" bgcolor="ffff66">
		   <th>아이디</th>
		   <th>비밀번호</th>
		   <th>이름</th>
		   <th>이메일</th>
		   <th>가입일자</th>
		   <th>취미</th>
		   <th>수정</th>
		   <th>삭제</th>
		</tr>
	
	<c:choose>	
	 <c:when test="${empty membersList}">
	 	 <tr>
	        <td colspan="8">
	          <b>등록된 회원이 없습니다.</b>
	       </td>  
        </tr>
	 </c:when>
	 <c:when test="${!empty membersList }">
		 <c:forEach var="mem" items="${membersList }">	
			<tr align="center">
		       <td>${mem.id}</td>
			   <td>${mem.pwd}</td>
			   <td>${mem.name}</td>
			   <td>${mem.email}</td>
			   <td>${mem.joinDate}</td>
			   <td>${mem.hobby}</td>
			   <td><a href="${contextPath}/memb/modMemberForm.do?id=${mem.id}">수정</a></td>
			   <td><a href="${contextPath}/memb/delMember.do?id=${mem.id}">삭제</a></td>
	     	</tr>
	     </c:forEach>	
     </c:when>
     </c:choose>
	</table>


</body>
</html>