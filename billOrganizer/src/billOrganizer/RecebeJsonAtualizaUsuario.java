package billOrganizer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/RecebeJsonAtualizaUsuario")
public class RecebeJsonAtualizaUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service (HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												IOException{
		DAO dao = new DAO();
		Usuarios usuario = new Usuarios();
		JSONObject jsonObject;
		JSONParser parser = new JSONParser();
		
		try{
			jsonObject = (JSONObject) parser.parse(request.getParameter("json"));
			usuario.setId((String) jsonObject.get("id"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonNome"));
			usuario.setNome((String) jsonObject.get("nome"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonCpf"));
			usuario.setCpf((String) jsonObject.get("cpf"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonRG"));
			usuario.setRG((String) jsonObject.get("RG"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonEmissor"));
			usuario.setEmissor((String) jsonObject.get("emissor"));
			dao.alteraUsuario(usuario);
		} catch (org.json.simple.parser.ParseException e1){
			e1.printStackTrace();
		}
		dao.close();
	}

}
