package billOrganizer;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/removeUsuario")
public class RemoveUsuario extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
    		throws ServletException, IOException {
       		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("RG: <input type='integer' name='RG'><br>");
		out.println("Emissor: <input type='text' name='emissor'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	
	@Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
    		throws ServletException, IOException {
	
    		DAO dao = new DAO();		
    		dao.removeUsuario(Integer.valueOf(request.getParameter("RG")),request.getParameter("emissor"));
       		PrintWriter out = response.getWriter();
       		out.println("<html><body>");
       		out.println("removido");
       		out.println("</body></html>");
		
	 dao.close();
    } 
}