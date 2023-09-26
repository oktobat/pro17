<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("UTF-8");
%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<c:set var="userId" value="${sessionScope.userid}" />
<c:set  var="articles"  value="${articlesMap.articlesList}" />
<c:set  var="totArticles"  value="${articlesMap.totArticles}" />
<c:set  var="section"  value="${articlesMap.section}" />
<c:set  var="pageNum"  value="${articlesMap.pageNum}" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   .no-uline {text-decoration:none;}
   .sel-page{text-decoration:none;color:red;}
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
</style>
</head>
<body>

<h2>자유게시판</h2>
<table border="1"  width="80%">
  <tr bgcolor="lightgreen">
     <td>글번호</td>
     <td>제목</td>              
     <td>작성자</td>
     <td>작성일</td>
  </tr>
  <c:forEach var="article" items="${articles}">
	  <tr>
	     <td >${article.articleNO }</td>
	     <td ><a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a></td>              
	     <td >${article.id }</td>
	     <td >${article.writeDate }</td>
	  </tr>
  </c:forEach>
</table>

<c:out value="${totArticles }" />
<div>
  <c:choose>
  	 <c:when test="${totArticles > 100}">
  	    <c:forEach var="page" begin="1" end="10" step="1">
  	    	<c:if test="${section>1 && page==1}">
  	    	   <a href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 }">preview</a>
  	    	</c:if>
  	    	<a href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 + page}</a>
  	    	<c:if test="${page==10}">
  	    	  <a href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">next</a>
  	    	</c:if>
  	    </c:forEach>
  	 </c:when>
  	 <c:when test="${totArticles==100}">
  	 	<c:forEach var="page" begin="1" end="10" step="1">
	        <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	    </c:forEach>
  	 </c:when>
  	 <c:when test="${totArticles<100}">
  	    <c:forEach  var="page" begin="1" end="${totArticles/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	           </c:when>
	           <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	           </c:otherwise>
	        </c:choose>
	      </c:forEach>
  	 </c:when>
  </c:choose>
</div>


<c:if test="${!empty userid}">
  <a href="${contextPath}/boardpage/articleForm.jsp">글쓰기</a>
</c:if>

</body>
</html>