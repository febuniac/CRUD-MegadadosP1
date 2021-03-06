import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/atualiza")
public class Atualiza extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
                           HttpServletResponse response) 
	throws ServletException, IOException {
	   PrintWriter out = response.getWriter();
	   out.println("<html><body>");
	   out.println("<form method='post'>");
	   out.println("ID: <input type='number' name='id'><br>");
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

		Pessoas pessoa = new Pessoas();
		pessoa.setId(Integer.valueOf(request.getParameter("id")));
		pessoa.setNome(request.getParameter("nome"));  
		pessoa.setEmissor(request.getParameter("emissor"));
		pessoa.setCpf(Integer.valueOf(request.getParameter("cpf")));
		pessoa.setRg(Integer.valueOf(request.getParameter("rg")));
		
		dao.altera(pessoa);
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("atualizado" + pessoa.getNome());
		out.println("</body></html>");
		
		dao.close();
		
	} 
}