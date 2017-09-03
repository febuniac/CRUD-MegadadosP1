package billOrganizer;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/listaUsuario")
public class ListaUsuario extends HttpServlet {

    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws  ServletException, IOException {

		DAO dao = new DAO();

		List<Usuarios> usuarios = dao.getListaUsuarios();
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>Nome</td>" + 
                    "<td>Emissor</td><td>CPF</td><td>RG</td></tr>");
		for (Usuarios usuario : usuarios) {
			out.println("<td >" + usuario.getNome() + "</td>");
	         out.println("<td >" + usuario.getEmissor() + "</td>");
	         out.println("<td >" + usuario.getCpf() + "</td></tr>");
	         out.println("<td>" + usuario.getRG() + "</td></tr>");	
		}
		out.println("</table></body></html>");
        
		dao.close();
        
    } 
}