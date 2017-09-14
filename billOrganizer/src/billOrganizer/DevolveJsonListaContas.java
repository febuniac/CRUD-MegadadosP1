package billOrganizer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/DevolveJsonListaContas")
public class DevolveJsonListaContas extends HttpServlet {

    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws  ServletException, IOException {

		DAO dao = new DAO();
		
		String json = new Gson().toJson(dao.getListaContas());
		response.getWriter().write(json);
		System.out.println(json);
		dao.close();		
        
    } 
}