package billOrganizer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

@WebServlet("/RecebeDevolveJsonListaContasUsuario")
public class RecebeDevolveJsonListaContasUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service (HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												IOException{
		DAO dao = new DAO();
		JSONObject jsonObject;
		JSONParser parser = new JSONParser();
		
		try{
			jsonObject = (JSONObject) parser.parse(request.getParameter("json"));
			String json = new Gson().toJson(dao.getListaContasUsuario(Integer.valueOf(jsonObject.get("usuario_id").toString())));
			response.getWriter().write(json);
			System.out.println(json);
		} catch (org.json.simple.parser.ParseException e1){
			e1.printStackTrace();
		}
		dao.close();
	}

}