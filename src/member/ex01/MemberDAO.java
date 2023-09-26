package member.ex01;

import java.sql.Connection;
import java.sql.Date;
//import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/test");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	이름으로 회원검색
	public List<MemberBean> oklistMembers(MemberBean mvo){
		List<MemberBean> list = new ArrayList<MemberBean>();
		String _name = mvo.getName();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			if (_name != null && _name.length() != 0) {
				query += " where name=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, _name);
			} else {
				pstmt = con.prepareStatement(query);
			}
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				String hobby = rs.getString("hobby");
				MemberBean vo = new MemberBean(id, pwd, name, email, joinDate, hobby);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
//	회원목록
	public List<MemberBean> listMembers(){
		List<MemberBean> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				String hobby = rs.getString("hobby");
				MemberBean vo = new MemberBean(id, pwd, name, email, joinDate, hobby);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	회원가입
	public void addMember(MemberBean memberVO) {
		try { 
			con = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String hobby = memberVO.getHobby();
			String query = "insert into t_member";
			query += " (id, pwd, name, email, hobby)";
			query += " values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, hobby);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	
// 회원삭제	
	public void delMember(String id) {
		try {
//			connDB();
			con = dataFactory.getConnection();
			String query = "delete from t_member where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
//	로그인
	public String okMember(String id_, String pwd_) {
		String info = "";
		try {
		con = dataFactory.getConnection();
		String query = "select * from t_member where id=?";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, id_);
		ResultSet rs = pstmt.executeQuery();
		if ( rs.next() ) {
			String pwd = rs.getString("pwd");
			if (pwd.equals(pwd_)) {
				info = "회원";
			} else { 
				info = "비번틀림";
			}
		} else {
			info = "비회원";
		}
		rs.close();
		pstmt.close();
		con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return info;
	}
	
//	수정 아이디와 일치하는 레코드 검색해서 리턴하기
	public MemberBean findMember(String id){
		MemberBean memInfo = null;
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String _id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			String hobby = rs.getString("hobby");
			memInfo = new MemberBean(_id, pwd, name, email, joinDate, hobby);
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return memInfo;
	}
	
// 회원정보 수정
	public void modMember(MemberBean memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String hobby = memberVO.getHobby();
		System.out.println(id);
		try { 
			con = dataFactory.getConnection();
			String query = "update t_member set pwd=?, name=?, email=?, hobby=? where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, hobby);
			pstmt.setString(5, id);
			System.out.println(query);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}	
	
	
	
//	private void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("MySQL 드라이버 로딩 성공");
//			con = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");
////			stmt = con.createStatement();
////			System.out.println("Statement 생성 성공");
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
}
