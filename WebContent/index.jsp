<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"
%>
<%
  request.setCharacterEncoding("UTF-8");
%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<c:set var="userId" value="${sessionScope.userid}" />   
<c:set var="info" value="${requestScope.info}" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      
      <c:choose>
      	<c:when test="${info=='회원'}">
      		<script>
		         window.onload=function(){
		            alert("회원임이 확인됐습니다.");
		         }
	        </script>
      	</c:when>
      	<c:when test="${info=='비번틀림'}">
			<script>
		         window.onload=function(){
		            alert("비밀번호가 틀립니다.");
		         }
	        </script>      	
      	</c:when>
      	<c:when test="${info=='비회원'}">
      		<script>
		         window.onload=function(){
		            alert("아이디가 일치하지 않습니다.");
		         }
	        </script>
      	</c:when>
      </c:choose>

	<ul>
	   <c:if test="${empty userId}">
		   <li><a href="${contextPath}/member/memberForm.jsp">회원가입</a></li>
		   <li><a href="${contextPath}/member/loginForm.jsp">로그인</a></li>
	   </c:if>	
	   <c:if test="${!empty userId}">
		   <li><a href="${contextPath}/memb/modMemberForm.do?id=${userId}">정보수정</a></li>
		   <li><a href="${contextPath}/memb/logout.do">로그아웃</a></li>
	   </c:if>
	   	   <li><a href="${contextPath}/board/listArticles.do">자유게시판</a></li>
	</ul>
	
</body>
</html>