<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="userId" value="${sessionScope.userid}"/>   
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js" integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
function readURL(input){
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e){
			// console.log(e.target.result)
			$('#preview').attr('src', e.target.result)
		}
		reader.readAsDataURL(input.files[0])		
	}
}
</script>
</head>
<body>
<h1 style="text-align:center">새글 쓰기</h1>
  <form name="articleForm" method="post" action="${contextPath}/board/addArticle.do" enctype="multipart/form-data">
    <table border="0" align="center">
     <tr>
	   <td align="right">작성자: </td>
	   <td colspan="2"><input type="text" size="67"  name="gid" value="${userId}" disabled />
	   <input type="hidden" size="67"  name="id" value="${userId}" />
	   </td>
	 </tr>
     <tr>
	   <td align="right">글제목: </td>
	   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" /></td>
	 </tr>
	 <tr>
		<td align="right" valign="top"><br>글내용: </td>
		<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
     </tr>
     <tr>
        <td align="right">이미지파일 첨부:  </td>
	     <td> <input type="file" name="imageFileName" onchange="readURL(this)" /></td>
         <td><img  id="preview" src="#"   width=200 height=200/></td>
	 </tr>
    </table>
    <input type="submit" value="글쓰기" />
	<a href="${contextPath}/board/listArticles.do">목록</a>
  </form>
</body>
</html>
