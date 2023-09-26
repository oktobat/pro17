package board.ex01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		//articlesMap.put("totArticles", 170);
		return articlesMap;
	}
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	public ArticleVO viewArticle(int articleNO) {
		return boardDAO.selectArticle(articleNO);
	}
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	public void removeArticle(int articleNO) {
		boardDAO.selectRemovedArticles(articleNO);
	}
	
}
