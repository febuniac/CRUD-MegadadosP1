package billOrganizer;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
@WebServlet("/DevolveJsonListaUsuarios")
public class DevolveJsonListaUsuarios extends HttpServlet {

    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws  ServletException, IOException {

		DAO dao = new DAO();
		
		String json = new Gson().toJson(dao.getListaUsuarios());
		response.getWriter().write(json);
		System.out.println(json);
		dao.close();		
        
    } 
}

/*
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
*/