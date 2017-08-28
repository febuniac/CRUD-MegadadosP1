


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removebyname")
public class RemovebyName extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
    		throws ServletException, IOException {
       		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Remover ID: <input type='text' name='nome'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	
	@Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
    		throws ServletException, IOException {
	
    		DAO dao = new DAO();		
		dao.removebyname(String.valueOf(request.getParameter("nome")));
		
       		PrintWriter out = response.getWriter();
       		out.println("<html><body>");
       		out.println("removed");
       		out.println("</body></html>");
		
	 dao.close();
    } 
}
