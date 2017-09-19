package billOrganizer;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/RecebeJsonConta")
public class RecebeJsonConta extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service (HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												IOException{
		
		
		request.getParameter("json");
		DAO dao = new DAO();
		Contas conta = new Contas();
		JSONObject jsonObject;
		JSONParser parser = new JSONParser();
		
		try{
			jsonObject = (JSONObject) parser.parse(request.getParameter("json"));
			//usuario.setId((String) jsonObject.get("id"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonNome"));
			conta.setEmissor((String) jsonObject.get("emissor"));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonCpf"));
			System.out.println( jsonObject.get("vencimento"));
			//JSONArray venc= (JSONArray) jsonObject.get("vencimento");
			//jsonObjectV = (JSONObject) parserV.parse( (String) jsonObject.get("vencimento"));
			JSONObject object = (JSONObject) jsonObject.get("vencimento");
			System.out.println(object.get("year"));
			
	        int year = Integer.valueOf(object.get("year").toString());
	        int month = Integer.valueOf(object.get("month").toString());
	        int dayOfMonth = Integer.valueOf(object.get("dayOfMonth").toString());
	        int hourOfDay = Integer.valueOf(object.get("hourOfDay").toString());
	        int minute = Integer.valueOf(object.get("minute").toString());
	        int second = Integer.valueOf(object.get("second").toString());

	        Calendar c = Calendar.getInstance();
	        c.set(year, month, dayOfMonth, hourOfDay, minute, second);
			conta.setVencimento(c);
			
			
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonRG"));
			conta.setValor(Integer.valueOf(jsonObject.get("valor").toString()));
			conta.setUsuario_id(Integer.valueOf(jsonObject.get("usuario_id").toString()));
			//jsonObject = (JSONObject) parser.parse(request.getParameter("jsonEmissor"));
			dao.adicionaConta(conta);
		} catch (org.json.simple.parser.ParseException e1){
			e1.printStackTrace();
		}
		
		dao.close();
	}

}
