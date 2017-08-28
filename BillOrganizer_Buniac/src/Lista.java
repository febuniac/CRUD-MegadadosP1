//Este código carrega tudo que há na tabela especificada e mostra para o usuário.
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/lista")
public class Lista extends HttpServlet {

    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws  ServletException, IOException {

		DAO dao = new DAO();

		List<Pessoas> pessoas = dao.getLista();
		
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Nome</td>" + 
                    "<td>Emissor</td><td>CPF</td><td>RG</td></tr>");
		for (Pessoas pessoa : pessoas) {
	         out.println("<tr><td>" + pessoa.getId() + "</td>");
	         out.println("<td>" + pessoa.getNome() + "</td>");
	         out.println("<td>" + pessoa.getEmissor() + "</td>");
	         out.println("<td>" + pessoa.getCpf() + "</td></tr>");
	         out.println("<td>" + pessoa.getRg() + "</td></tr>");	
		}
		out.println("</table></body></html>");
        
		dao.close();
        
    } 
}