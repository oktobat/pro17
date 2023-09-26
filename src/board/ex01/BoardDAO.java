package board.ex01;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.ex01.MemberBean;

public class BoardDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articlesList = new ArrayList<>();
		int section = pagingMap.get("section");
		int pageNum = pagingMap.get("pageNum");
		try {
			con = dataFactory.getConnection();
			String query = "select B.* from (select @rownum := @rownum+1 rn, A.* from freeboard A, (select @rownum:=0) R) B where rn between (?-1)*100+(?-1)*10+1 and (?-1)*100+?*10 ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				  int articleNO = rs.getInt("articleNO");
			      int parentNO = rs.getInt("parentNO");
			      String title = rs.getString("title");
			      String content = rs.getString("content");
			      String id = rs.getString("id");
			      Date writeDate= rs.getDate("writeDate");
			      ArticleVO article = new ArticleVO();
			      article.setArticleNO(articleNO);
			      article.setParentNO(parentNO);
			      article.setTitle(title);
			      article.setContent(content);
			      article.setId(id);
			      article.setWriteDate(writeDate);
			      articlesList.add(article);	
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	public int insertNewArticle(ArticleVO article) {
		
		try {
			con = dataFactory.getConnection();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO freeboard ";
			query += " (title, content, imageFileName, id) ";
			query += " VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, imageFileName);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			
			String query1 = "SELECT  max(articleNO) from freeboard ";
			pstmt = con.prepareStatement(query1);
			ResultSet rs1 = pstmt.executeQuery();
			if (rs1.next()) {
				return (rs1.getInt(1));
			}
			
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 상세보기글 레코드 검색
		public ArticleVO selectArticle(int articleNO) {
			ArticleVO article = new ArticleVO();
			try {
				con = dataFactory.getConnection();
				String query = "select articleNO,parentNO,title,content, imageFileName, id, writeDate";
				query += " from freeboard";
				query += " where articleNO=?";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, articleNO);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int _articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String imageFileName = rs.getString("imageFileName");
				if (imageFileName==null) {
					imageFileName = "null";
				} else {
					imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8");  // 파일명이 한글인 경우 필요함
				}
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");

				article.setArticleNO(_articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setImageFileName(imageFileName);
				article.setId(id);
				article.setWriteDate(writeDate);
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return article;
		}
	
		public void updateArticle(ArticleVO article) {
			int articleNO = article.getArticleNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			try {
				con = dataFactory.getConnection();
				String query = "update freeboard  set title=?,content=?";
				if (imageFileName != null && imageFileName.length() != 0) {
					query += ",imageFileName=?";
				}
				query += " where articleNO=?";

				System.out.println(query);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				if (imageFileName != null && imageFileName.length() != 0) {
					pstmt.setString(3, imageFileName);
					pstmt.setInt(4, articleNO);
				} else {
					pstmt.setInt(3, articleNO);
				}
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void selectRemovedArticles(int articleNO) {
			try {
				con = dataFactory.getConnection();
				String query = "DELETE FROM freeboard ";
				query += " WHERE articleNO=?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, articleNO);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		public int selectTotArticles() {
			try {
				con = dataFactory.getConnection();
				String query = "select count(articleNO) from freeboard ";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					return (rs.getInt(1));
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		
// DAO 마지막괄호		
}
