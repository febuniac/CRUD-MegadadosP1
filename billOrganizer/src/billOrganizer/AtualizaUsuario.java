package billOrganizer;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/atualizaUsuario")
public class AtualizaUsuario extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
                           HttpServletResponse response) 
	throws ServletException, IOException {
	   PrintWriter out = response.getWriter();
	   out.println("<html><body>");
	   out.println("<form method='post'>");
	   out.println("Nome: <input type='text' name='nome'><br>");
	   out.println("Emissor: <input type='text' name='emissor'><br>");
	   out.println("CPF: <input type='integer' name='cpf'><br>");
	   out.println("RG: <input type='integer' name='rg'><br>");
	   out.println("<input type='submit' value='Submit'>");
	   out.println("</form>");
	   out.println("<body><html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
	throws ServletException, IOException {
		
		DAO dao = new DAO();

		Usuarios usuario = new Usuarios();
		//usuario.setId(Integer.valueOf(request.getParameter("id")));
		usuario.setNome(request.getParameter("nome"));  
		usuario.setEmissor(request.getParameter("emissor"));
		usuario.setCpf(Integer.valueOf(request.getParameter("cpf")));
		usuario.setRG(Integer.valueOf(request.getParameter("rg")));
		
		dao.alteraUsuario(usuario);
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado" + usuario.getNome());
		out.println("</body></html>");
		
		dao.close();
		
	} 
}
