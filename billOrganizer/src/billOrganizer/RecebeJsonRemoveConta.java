package billOrganizer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@WebServlet("/RecebeJsonRemoveConta")
public class RecebeJsonRemoveConta extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service (HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												IOException{
		DAO dao = new DAO();
		JSONObject jsonObject;
		JSONParser parser = new JSONParser();
		
		try{
			jsonObject = (JSONObject) parser.parse(request.getParameter("json"));
			dao.removeConta(Integer.valueOf(jsonObject.get("id").toString()));
		} catch (org.json.simple.parser.ParseException e1){
			e1.printStackTrace();
		}
		dao.close();
	}

}