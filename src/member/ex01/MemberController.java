package member.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MemberDAO memberDAO;
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		if (action==null || action.equals("/listMembers.do") ) {
			List<MemberBean> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/member/listMembers.jsp";
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String _email = request.getParameter("email");
			String _edomain = request.getParameter("edomain");
		    String email = _email + "@" + _edomain;
	        String[] arrhobby = request.getParameterValues("hobby");
	        String hobby = "";
		      if (arrhobby != null) {
		    	  for (int i=0; i<arrhobby.length; i++) {
		    		  if (i==arrhobby.length-1) {
		    			  hobby += arrhobby[i];
		    		  } else {
		    			  hobby += arrhobby[i]+"/";
		    		  }
				  }
		      }
		      MemberBean memberBean = new MemberBean(id, pwd, name, email, hobby);
		      memberDAO.addMember(memberBean);
		      nextPage = "/memb/listMembers.do";
		} else if (action.equals("/modMemberForm.do")) {
			  String id=request.getParameter("id");
			  MemberBean memInfo = memberDAO.findMember(id);
			  request.setAttribute("memInfo", memInfo);
			  nextPage="/member/modMemberForm.jsp";
		} else if (action.equals("/modMember.do")) {
			String id = request.getParameter("gid");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String _email = request.getParameter("email");
			String _edomain = request.getParameter("edomain");
		    String email = _email + "@" + _edomain;
	        String[] arrhobby = request.getParameterValues("hobby");
	        String hobby = "";
		      if (arrhobby != null) {
		    	  for (int i=0; i<arrhobby.length; i++) {
		    		  if (i==arrhobby.length-1) {
		    			  hobby += arrhobby[i];
		    		  } else {
		    			  hobby += arrhobby[i]+"/";
		    		  }
				  }
		      }
		      MemberBean memberBean = new MemberBean(id, pwd, name, email, hobby);
		      memberDAO.modMember(memberBean);
		      nextPage = "/memb/listMembers.do";
		} else if (action.equals("/delMember.do")) {
			 String id=request.getParameter("id");
			 memberDAO.delMember(id);
			 nextPage = "/memb/listMembers.do";
		} else if (action.equals("/login.do")) {
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String info = memberDAO.okMember(id, pwd);
			request.setAttribute("info", info);
			if (info.equals("회원")) {
				HttpSession session = request.getSession();
				session.setAttribute("userid", id);
			} 
			nextPage="/index.jsp";
		} else if (action.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			nextPage="/index.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
