package board.ex01;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
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
